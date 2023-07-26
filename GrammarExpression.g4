//TODO: expressions inside for loops and if aren't supposed to evaluated?
//TODO: explicitly negative numbers or expressions not supported

grammar GrammarExpression;

@header {
    import java.util.List;
    import java.util.ArrayList;
    import java.util.Stack;
    import compiler.structures.DataType;
    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.exceptions.SemanticException;
    import compiler.expressions.ExpressionConverter;
    import compiler.expressions.PostfixExpression;
    import compiler.ast.*;
}

@members {
    private SymbolTable symbolTable = new SymbolTable();
    private DataType currentType;
    private DataType leftDT;
    private DataType rightDT;
    private StringBuilder expression;
    private StringBuilder rawExpression;
    private List<String> variablesList;
    private String _lRelExp;
    private String _rRelExp;
    private String _relOp;
    private String _forInit;
    private String _forCondition;
    private String _forIncrement;
    private String _attribVariable;

    private Program program = new Program();
    private Stack<List<AbstractCommand>> stack = new Stack<>();

    public void init() {
        stack.push(new ArrayList<AbstractCommand>());
    }

    public void showSymbols() {
        symbolTable.get_all().stream().forEach((id)->System.out.println(id));
    }

    public void verifyDeclaration(String name) {
        if (!symbolTable.exists(name)) {
            throw new SemanticException("Variable '" + name + "' not declared");
        }
    }

    public Symbol getCheckedSymbol(String name) {
        verifyDeclaration(name);
        return symbolTable.get_symbol(name);
    }

    public void generateCTarget() {
        program.generateCTarget();
    }

    public void generateJavaTarget() {
        program.generateJavaTarget();
    }

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
 *
 */
declara      : type { variablesList = new ArrayList<>(); } varlist DOT {
                   CmdDeclare _declare = new CmdDeclare(currentType, variablesList);
                   stack.peek().add(_declare);
               }
             ;

type         : 'integer' { currentType = DataType.INTEGER; }
             | 'real' { currentType = DataType.REAL; }
             | 'string' { currentType = DataType.STRING; }
             ;

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

cmdexpr      : ID {
                  Symbol assigned_variable = getCheckedSymbol(_input.LT(-1).getText());
                  leftDT = assigned_variable.getType();
                  rightDT = null;
                  _attribVariable = _input.LT(-1).getText();
               }
               { 
                   expression = new StringBuilder();
                   rawExpression = new StringBuilder();
               } 
               ASSIGN
               (
                   expr {
                       if (leftDT != DataType.INTEGER && leftDT != DataType.REAL) {
                           throw new SemanticException("Can not assign to string variables through expressions");
                       }
                       PostfixExpression postfixExpression = ExpressionConverter.infixToPostfix(expression.toString());
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
                   CmdAttrib _attrib = new CmdAttrib(assigned_variable, rawExpression.toString().replace(",", "."));
                   stack.peek().add(_attrib);
               }
             ;

cmdif        : IF {
                   stack.push(new ArrayList<AbstractCommand>());
                   CmdIf _if = new CmdIf();
               } LPARENTHESIS relexpr RPARENTHESIS THEN LCURLY bloco {
                   _if.setTrueList(stack.pop());
               } RCURLY
               (
                   ELSE {
                       stack.push(new ArrayList<AbstractCommand>());
                   } LCURLY bloco RCURLY {
                       _if.setFalseList(stack.pop());
                   }
               )?
               { 
                   _if.setExpression(_lRelExp + _relOp + _rRelExp);
                   stack.peek().add(_if);
               }
             ;

cmdfor       : FOR {
                   stack.push(new ArrayList<AbstractCommand>());
                   CmdFor _for = new CmdFor();
               } LPARENTHESIS cmdexpr {
                   stack.peek().remove(stack.peek().size() - 1);
                   _forInit = _attribVariable + "=" + rawExpression.toString().replace(",", ".");
               } DOT relexpr {
                   _forCondition = _lRelExp + _relOp + _rRelExp;
               } DOT cmdexpr {
                   stack.peek().remove(stack.peek().size() - 1);
                   _forIncrement = _attribVariable + "=" + rawExpression.toString().replace(",", ".");
               } RPARENTHESIS LCURLY bloco RCURLY {
                   _for.setCmdList(stack.pop());
                   _for.setInit(_forInit);
                   _for.setCondition(_forCondition);
                   _for.setIncrement(_forIncrement);
                   stack.peek().add(_for);
               }
             ;

cmdwhile     : WHILE {
                  stack.push(new ArrayList<AbstractCommand>());
                  CmdWhile _while = new CmdWhile();
               } LPARENTHESIS relexpr RPARENTHESIS LCURLY bloco {
                   _while.setCmdList(stack.pop());
               } RCURLY
               {
                   _while.setExpression(_lRelExp + _relOp + _rRelExp);
                   stack.peek().add(_while);
               }
             ;

relexpr      : { rawExpression = new StringBuilder(); }
               expr {
                   _lRelExp = rawExpression.toString().replace(",", ".");
               }
               RELOP {
                   _relOp = _input.LT(-1).getText();
               }
               { rawExpression = new StringBuilder(); }
               expr {
                   _rRelExp = rawExpression.toString().replace(",", ".");
               }
             ;

expr         : termo exprl
             ;

exprl        : (PLUS | MINUS) {
                   expression.append(_input.LT(-1).getText());
                   rawExpression.append(_input.LT(-1).getText());
               }
               termo exprl |
             ;

termo        : fator termol
             ;

termol       : (MULT | DIV) {
                   expression.append(_input.LT(-1).getText());
                   rawExpression.append(_input.LT(-1).getText());
               } fator termol |
             ;

fator        : NUM {
                   String number = _input.LT(-1).getText();
                   if (number.contains(String.valueOf(',')) && leftDT != DataType.REAL) {
                       throw new SemanticException("REAL value in a " + leftDT + " type expression");
                   } else if (!number.contains(String.valueOf(',')) && leftDT != DataType.INTEGER) {
                       throw new SemanticException("INTEGER value in a " + leftDT + " type expression");
                   }
                   expression.append(_input.LT(-1).getText());
                   rawExpression.append(_input.LT(-1).getText());
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
                   expression.append(operand.getValue());
                   rawExpression.append(operand.getName());
                   operand.setUsed(true);
               }
               | LPARENTHESIS {
                   expression.append('(');
                   rawExpression.append('(');
               } expr RPARENTHESIS {
                   expression.append(')');
                   rawExpression.append(')');
               }
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
