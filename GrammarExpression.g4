grammar GrammarExpression;

@header {
    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.structures.DataType;
    import compiler.exceptions.SemanticException;
    import java.util.ArrayList;
}

@members {
    private SymbolTable symbolTable = new SymbolTable();
    private DataType currentType;

    public void showSymbols() {
        ArrayList<Symbol> idList = symbolTable.get_all();
        idList.stream().forEach((id)->System.out.println(id));
    }

    public void verifyDeclaration(String name) {
        if (!symbolTable.exists(name)) {
            throw new SemanticException("Variable '" + name + "' not declared");
        }
    }
}

programa     : PROGRAM declara+ bloco ENDPROG DOT
             ;

declara      : type varlist DOT
             ;

type         : 'int' { currentType = DataType.INTEGER; }
             | 'float' { currentType = DataType.FLOAT; }
             | 'string' { currentType = DataType.STRING; }
             ;

varlist      : ID { symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType)); }
               (
                   COMMA
                   ID { symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType)); }
               )*
             ; 

bloco        : (cmd)+
             ;

cmd          : cmdleitura DOT | cmdescrita DOT | cmdexpr DOT | cmdif | cmdfor | cmdwhile
             ;

cmdleitura   : READ LPARENTHESIS ID { verifyDeclaration(_input.LT(-1).getText()); } RPARENTHESIS
             ;

cmdescrita   : WRITE LPARENTHESIS (TEXT | ID { verifyDeclaration(_input.LT(-1).getText()); }) RPARENTHESIS
             ;

cmdexpr      : ID { verifyDeclaration(_input.LT(-1).getText()); } ASSIGN expr
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

exprl        : PLUS termo exprl | MINUS termo exprl |
             ;

termo        : fator termol
             ;

termol       : MULT fator termol | DIV fator termol |
             ;

fator        : NUM | ID { verifyDeclaration(_input.LT(-1).getText()); }| LPARENTHESIS expr RPARENTHESIS
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

FOR          : 'for'
             ;

WHILE        : 'while'
             ;

RELOP        : '<' | '>' | '<=' | '>=' | '!=' | '=='
             ;

TEXT         : DQUOTE ([0-9] | [a-z] | [A-Z] | ' ')+ DQUOTE
             ;

NUM          : [0-9]+ (COMMA [0-9]+)?
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
