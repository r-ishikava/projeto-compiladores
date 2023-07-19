//TODO: expressions inside for loops and if aren't supposed to evaluated?

grammar GrammarExpression;

@header {
    import java.util.ArrayList;
    import compiler.structures.DataType;
    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.exceptions.SemanticException;
    import compiler.expressions.ExpressionConverter;
    import compiler.expressions.PostfixExpression;
}

@members {
    private SymbolTable symbolTable = new SymbolTable();
    private DataType currentType;
    private DataType leftDT;
    private DataType rightDT;
    private DataType numberDT;
    private StringBuilder expression;

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
}

programa     : PROGRAM declara+ bloco ENDPROG DOT
             ;

declara      : type varlist DOT
             ;

type         : 'integer' { currentType = DataType.INTEGER; }
             | 'real' { currentType = DataType.REAL; }
             | 'string' { currentType = DataType.STRING; }
             ;

varlist      : ID {
                   symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
               }
               (
                   COMMA
                   ID {
                       symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
                   }
               )*
             ; 

bloco        : (cmd)+
             ;

cmd          : cmdleitura DOT | cmdescrita DOT | cmdexpr DOT | cmdif | cmdfor | cmdwhile
             ;

cmdleitura   : READ LPARENTHESIS
               ID {
                   verifyDeclaration(_input.LT(-1).getText()); 
               }
               RPARENTHESIS
             ;

cmdescrita   : WRITE LPARENTHESIS
               (
                   TEXT
                   |
                   ID {
                       verifyDeclaration(_input.LT(-1).getText());
                   }
               ) RPARENTHESIS
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
               termo {

               }
               exprl |
             ;

termo        : fator termol
             ;

termol       : (MULT | DIV) {
                   expression.append(_input.LT(-1).getText());
               } fator {

               }
               termol |
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
                       throw new SemanticException("Use of uninitialized variable '" + operand.getName() + "'");
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
