//TODO: expressions inside for loops and if statements aren't supposed to evaluated?
//TODO: explicitly negative numbers or expressions may not supported
//TODO: exprl and termol may contain empty productions
//TODO: add line and column for the offending symbol in error messages
//TODO: divisions by zero should not be caught in compilation time?
//TODO: strings should not be used in for initialization and incrementation?
//TODO: may not need to evalutate the value from expressions
//TODO: language inconsistencies

grammar GrammarExpression;

@header {
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Stack;
    import compiler.structures.DataType;
    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.exceptions.SemanticException;
    import compiler.expressions.PostfixExpression;
    import compiler.expressions.ArithmeticExpression;
    import compiler.expressions.RelationalExpression;
    import compiler.expressions.BooleanExpression;
    import compiler.ast.*;
}

@members {
    private SymbolTable symbolTable;
    private DataType currentType;
    private DataType leftDT;
    private StringBuilder expression;
    private ArithmeticExpression arithmeticExpression;
    private RelationalExpression relationalExpression;
    private BooleanExpression booleanExpression;
    private List<String> variablesList;
    private String _attribVariable;

    private Program program;
    private Stack<List<AbstractCommand>> stack;

    /**
     * Initialize the program's components
     */
    public void init() {
        program = new Program();
        stack = new Stack<>();
        symbolTable = new SymbolTable();
        stack.push(new ArrayList<AbstractCommand>());
    }

    /**
     * Show the symbol table
     */
    public void showSymbols() {
        symbolTable.get_all().stream().forEach((id)->System.out.println(id));
    }

    /**
     * Verify if a variable is declared, i.e symbol is in the symbol table
     */
    public void verifyDeclaration(String name) {
        if (!symbolTable.exists(name)) {
            throw new SemanticException("Variable '" + name + "' not declared");
        }
    }

    /**
     * Gets symbol by name, raises exception if variable is not declared
     */
    public Symbol getCheckedSymbol(String name) {
        verifyDeclaration(name);
        return symbolTable.get_symbol(name);
    }

    public void generateCTarget(String filename) {
        program.generateCTarget(filename);
    }

    public void generateJavaTarget(String filename) {
        program.generateJavaTarget(filename);
    }

    /**
     * Gives warnings if declared variables are not used in expressions or on the write command
     */
    public void unusedWarning() {
        for (Symbol symbol : symbolTable.get_all()) {
            if (!symbol.getUsed()) {
                System.out.println("Warning: variable '" + symbol.getName() + "' was never used");
            }
        }
    }
}

/**
 * programa
 *     declara+
 *     commands
 * fimprog.
 */
programa     : PROGRAM declara+ bloco ENDPROG DOT {
                   program.setCommands(stack.pop());
                   unusedWarning();
               }
             ;

/**
 * type varlist.
 * int a, b, c.
 */
declara      : type { variablesList = new ArrayList<>(); } varlist DOT {
                   CmdDeclare _declare = new CmdDeclare(currentType, variablesList);
                   stack.peek().add(_declare);
               }
             ;

/**
 * (integer | real | string)
 */
type         : 'integer' { currentType = DataType.INTEGER; }
             | 'real' { currentType = DataType.REAL; }
             | 'string' { currentType = DataType.STRING; }
             ;

/**
 * ID (, ID)*
 * a, b, c.
 */
varlist      : ID {
                   symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
                   variablesList.add(_input.LT(-1).getText());
               }
               (
                   COMMA
                   ID {
                       symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
                       variablesList.add(_input.LT(-1).getText());
                   }
               )*
             ; 

bloco        : (cmd)+
             ;

cmd          : cmdleitura | cmdescrita | cmdexpr DOT | cmdif | cmdfor | cmdwhile
             ;

/**
 * leia(ID).
 */
cmdleitura   : READ LPARENTHESIS
               ID {
                   Symbol symbol = getCheckedSymbol(_input.LT(-1).getText()); 
                   //Set variable value to 0 to not trigger the uninitialized error
                   symbol.setValue("0");
                   CmdRead _read = new CmdRead(symbol);
                   stack.peek().add(_read);
               }
               RPARENTHESIS DOT
             ;

/**
 * escreva(ID | TEXT).
 */
cmdescrita   : WRITE LPARENTHESIS
               (
                   TEXT {
                       CmdWrite _write  = new CmdWrite(_input.LT(-1).getText());
                       stack.peek().add(_write);
                   }
                   |
                   ID {
                       Symbol symbol = getCheckedSymbol(_input.LT(-1).getText());
                       if (symbol.getValue() == null) {
                           throw new SemanticException("Trying to read from an uninitialized variable: '" + symbol.getName() + "'");
                       }
                       CmdWrite _write = new CmdWrite(symbol);
                       stack.peek().add(_write);
                       symbol.setUsed(true);
                   }
               ) RPARENTHESIS DOT
             ;

/**
 * ID := (expr | TEXT).
 * a := 1 + 2.
 * a := "string".
 */
cmdexpr      : ID {
                  Symbol assigned_variable = getCheckedSymbol(_input.LT(-1).getText());
                  leftDT = assigned_variable.getType();
                  _attribVariable = _input.LT(-1).getText();
               }
               ASSIGN
               (
                   expr {
                       if (leftDT != DataType.INTEGER && leftDT != DataType.REAL) {
                           throw new SemanticException("Can not assign to string variables through expressions");
                       }
                       PostfixExpression postfixExpression = new PostfixExpression(expression.toString(), symbolTable);
                       String result = postfixExpression.calculate().replace('.', ',');
                       assigned_variable.setValue(result);
                       symbolTable.add_symbol(assigned_variable);
                   }
                   |
                   TEXT {
                       if (leftDT != DataType.STRING) {
                           throw new SemanticException("Tried to assign a string value to a " + leftDT + " variable");
                       }
                       assigned_variable.setValue(_input.LT(-1).getText());
                       symbolTable.add_symbol(assigned_variable);
                   }
               )
               {
                   CmdAttrib _attrib;
                   if (expression == null) {
                       _attrib = new CmdAttrib(assigned_variable, "");
                   } else {
                       _attrib = new CmdAttrib(assigned_variable, expression.toString().replace(",", "."));
                   }
                   stack.peek().add(_attrib);
                   expression = null;
               }
             ;

/**
 * se ( boolexpr ) entao {
 *     bloco  
 * } (senao {
 *     bloco  
 * })?
 */
cmdif        : IF {
                   stack.push(new ArrayList<AbstractCommand>());
                   CmdIf _if = new CmdIf();
               }
               LPARENTHESIS boolexpr {
                   _if.setExpression(booleanExpression);
                   booleanExpression = null;
               }
               RPARENTHESIS THEN LCURLY bloco {
                   _if.setTrueList(stack.pop());
               }
               RCURLY
               (
                   ELSE {
                       stack.push(new ArrayList<AbstractCommand>());
                   }
                   LCURLY bloco RCURLY {
                       _if.setFalseList(stack.pop());
                   }
               )?
               { stack.peek().add(_if); }
             ;

/**
 * para ( cmdexpr. boolexpr. cmdexpr ) {
 *     bloco  
 * }
 */
cmdfor       : FOR {
                   stack.push(new ArrayList<AbstractCommand>());
                   CmdFor _for = new CmdFor();
               }
               LPARENTHESIS cmdexpr {
                   //Remove last command from stack, otherwise cmdexpr from the loop declaration will be added as a command
                   stack.peek().remove(stack.peek().size() - 1);
                   _for.setInit(_attribVariable + "=" + arithmeticExpression.toString().replace(",", "."));
               }
               DOT boolexpr {
                   _for.setCondition(booleanExpression);
                   booleanExpression = null;
               }
               DOT cmdexpr {
                   //Same as previous
                   stack.peek().remove(stack.peek().size() - 1);
                   _for.setIncrement(_attribVariable + "=" + arithmeticExpression.toString().replace(",", "."));
               }
               RPARENTHESIS LCURLY bloco RCURLY {
                   _for.setCmdList(stack.pop());
                   stack.peek().add(_for);
               }
             ;

/**
 * enquanto ( boolexpr ) {
 *     bloco  
 * }
 */
cmdwhile     : WHILE {
                  stack.push(new ArrayList<AbstractCommand>());
                  CmdWhile _while = new CmdWhile();
               }
               LPARENTHESIS boolexpr {
                   _while.setExpression(booleanExpression);
                   booleanExpression = null;
               }
               RPARENTHESIS LCURLY bloco {
                   _while.setCmdList(stack.pop());
               }
               RCURLY
               { stack.peek().add(_while); }
             ;

/**
 * expr RELOP expr
 */
relexpr      : {
                   relationalExpression = new RelationalExpression();
                   relationalExpression.setSymbolTable(symbolTable);
               }
               expr {
                   relationalExpression.setLeftSide(arithmeticExpression);
               }
               { expression = null; }
               RELOP {
                   relationalExpression.setOperator(_input.LT(-1).getText());
               }
               expr {
                   relationalExpression.setRightSide(arithmeticExpression);
               }
               { expression = null; }
             ;

/**
 * termo expr
 * a := 1 + 2 - 3 * 4 / 5 * (6 + 7)
 */
expr         : {
                   //Need to check if the expression is null because of recursion
                   if (expression == null) {
                       expression = new StringBuilder();
                   }
               }
               termo exprl
               { arithmeticExpression = new ArithmeticExpression(expression.toString(), symbolTable);}
             ;

/**
 * (+ | -) termo expr
 */
exprl        : (PLUS | MINUS) {
                   expression.append(_input.LT(-1).getText());
               }
               termo exprl |
             ;

termo        : fator termol
             ;

/**
 * (* | /) fator termol
 */
termol       : (MULT | DIV) {
                   expression.append(_input.LT(-1).getText());
               }
               fator termol |
             ;

/**
 * NUM | ID | ( expr )
 */
fator        : NUM {
                   String number = _input.LT(-1).getText();
                   if (number.contains(String.valueOf(',')) && leftDT != DataType.REAL) {
                       throw new SemanticException("REAL value in a " + leftDT + " type expression");
                   } else if (!number.contains(String.valueOf(',')) && leftDT != DataType.INTEGER) {
                       throw new SemanticException("INTEGER value in a " + leftDT + " type expression");
                   }
                   expression.append(_input.LT(-1).getText());
               }
               |
               ID {
                   Symbol operand = getCheckedSymbol(_input.LT(-1).getText());
                   if (operand.getType() != leftDT) {
                       throw new SemanticException("Variable of the " + operand.getType() + " type in a " + leftDT + " type expression");
                   }
                   if (operand.getValue() == null) {
                       throw new SemanticException("Use of uninitialized variable '" + operand.getName() + "' of the type " + operand.getType());
                   }
                   expression.append(operand.getName());
                   operand.setUsed(true);
               }
               |
               LPARENTHESIS {
                   expression.append('(');
               }
               expr
               RPARENTHESIS {
                   expression.append(')');
               }
             ;

/**
 * boolfactor (BOOLOP boolfactor)*
 */
boolexpr     : {
                   if (booleanExpression == null) {
                       booleanExpression = new BooleanExpression();
                   }
               }
               boolfactor (
                   BOOLOP { booleanExpression.append(_input.LT(-1).getText()); }
                   boolfactor
               )*
             ;

/**
 * relexpr | ( boolexpr )
 */
boolfactor   : relexpr { booleanExpression.append(relationalExpression.toString()); }
               |
               LPARENTHESIS { booleanExpression.append("("); }
               boolexpr
               RPARENTHESIS { booleanExpression.append(")"); }
             ;

PROGRAM      : 'programa'
             ;

ENDPROG      : 'fimprog'
             ;

DECLARE      : 'declare'
             ;

READ         : 'leia'
             ;

WRITE        : 'escreva'
             ;

IF           : 'se'
             ;

THEN         : 'entao'
             ;

ELSE         : 'senao'
             ;

FOR          : 'para'
             ;

WHILE        : 'enquanto'
             ;

RELOP        : '<' | '>' | '<=' | '>=' | '!=' | '=='
             ;

BOOLOP       : '&&' | '||'
             ;

TEXT         : DQUOTE ([0-9] | [a-z] | [A-Z] | ' ' | '\t' | '\'')* DQUOTE
             ;

NUM          : INT | REAL 
             ;

INT          : [0-9]+
             ;

REAL         : [0-9]+ COMMA [0-9]+
             ;

ID           : ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])*
             ;

COMMA        : ','
             ;

DOT          : '.'
             ;

LPARENTHESIS : '('
             ;

RPARENTHESIS : ')'
             ;

ASSIGN       : ':='  
             ;  

LCURLY       : '{'  
             ;  

RCURLY       : '}'  
             ;  

PLUS         : '+'  
             ;  

MINUS        : '-'
             ;

MULT         : '*'
             ;

DIV          : '/'
             ;

DQUOTE       : '"'
             ;

WS           : (' ' | '\t' | '\n' | '\r')+ -> skip
             ;  
