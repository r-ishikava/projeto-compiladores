//TODO: expressions inside for loops and if aren't supposed to evaluated?

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
    private List<String> variablesList;

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
}

programa     : PROGRAM declara+ bloco ENDPROG DOT {
                   program.setCommands(stack.pop());
               }
             ;

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
                   }
               ) RPARENTHESIS DOT
             ;

cmdexpr      : ID {
                  Symbol assigned_variable = getCheckedSymbol(_input.LT(-1).getText());
                  leftDT = assigned_variable.getType();
                  rightDT = null;
               }
               { expression = new StringBuilder(); } 
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
                   CmdAttrib _attrib = new CmdAttrib(assigned_variable, expression.toString());
                   stack.peek().add(_attrib);
               }
             ;

cmdif        : IF LPARENTHESIS relexpr RPARENTHESIS THEN LCURLY bloco RCURLY (ELSE LCURLY bloco RCURLY)?
             ;

cmdfor       : FOR LPARENTHESIS cmdexpr DOT relexpr DOT cmdexpr RPARENTHESIS LCURLY bloco RCURLY
             ;

cmdwhile     : WHILE LPARENTHESIS relexpr RPARENTHESIS LCURLY bloco RCURLY
             ;

relexpr      : expr RELOP expr
             ;

expr         : termo exprl
             ;

exprl        : (PLUS | MINUS) {
                   expression.append(_input.LT(-1).getText());
               }
               termo exprl |
             ;

termo        : fator termol
             ;

termol       : (MULT | DIV) {
                   expression.append(_input.LT(-1).getText());
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
               }
               | LPARENTHESIS { expression.append('('); } expr RPARENTHESIS { expression.append(')'); }
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
