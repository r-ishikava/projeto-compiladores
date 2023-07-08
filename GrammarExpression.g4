grammar GrammarExpression;

programa     : PROGRAM declara bloco ENDPROG DOT
             ;

declara      : DECLARE ID (COMMA ID)* DOT
             ;

bloco        : (cmd)+
             ;

cmd          : cmdleitura DOT | cmdescrita DOT | cmdexpr DOT | cmdif
             ;

cmdleitura   : READ LPARENTHESIS ID RPARENTHESIS
             ;

cmdescrita   : WRITE LPARENTHESIS (TEXT | ID) RPARENTHESIS
             ;

cmdexpr      : ID ASSIGN expr
             ;

cmdif        : IF LPARENTHESIS expr RELOP expr RPARENTHESIS THEN LCURLY bloco RCURLY (ELSE LCURLY bloco RCURLY)?
             ;

expr         : termo exprl
             ;

exprl        : PLUS termo exprl | MINUS termo exprl |
             ;

termo        : fator termol
             ;

termol       : MULT fator termol | DIV fator termol |
             ;

fator        : NUM | ID | LPARENTHESIS expr RPARENTHESIS
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

RELOP        : '<' | '>' | '<=' | '>=' | '!=' | '=='
             ;

TEXT         : DQUOTE ([0-9] | [a-z] | [A-Z] | ' ')+ DQUOTE
             ;

NUM          : [0-9]+
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
