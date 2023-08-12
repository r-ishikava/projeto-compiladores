# Projeto Compiladores isiLanguage
Projeto da disciplina de compiladores da UFABC

## Uso
Projeto desenvolvido em Java 20

Gerar parsers:  
`java -cp .:./lib/antlr-4.13.0-complete.jar org.antlr.v4.Tool ./GrammarExpression.g4 -package compiler.core -o ./src/compiler/core`

Compilar main class:  
`javac -cp ./lib/antlr-4.13.0-complete.jar -sourcepath ./src ./src/compiler/main/MainClass.java -d ./bin`

Executar main class com input.isi, gerando java e c:  
`java -cp bin:lib/antlr-4.13.0-complete.jar compiler.main.MainClass input.isi -java -c`

## Itens implementados
__Itens Obrigatórios__
- Possui 2 tipos de variáveis
  - inteiros
  - reais
  - strings
- Possui a estrutura if...else
- Possui estrutura de controle while
- Operaçãoes Aritméticas executadas corretamente
- Atribuições realizadas corretamente
- Possui operações de Entrada e Saída
- Aceita números decimais
- Verifica se a variável já foi previamente declarada
- Verifica se a variável foi declarada e não foi usada
- Verifica se uma variável está sendo usada sem ter valor inicial

__Itens Opcionais__
- Editor Highlight
- Avaliador de expressões aritméticas
- Inserção de Operadores lógicos
- Geração de várias linguagens-alvo
  - Java
  - C
