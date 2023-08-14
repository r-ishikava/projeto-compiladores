# Projeto Compiladores isiLanguage
Projeto da disciplina de compiladores da UFABC com professor Isidro

__Integrantes do grupo:__  
Bruno Seiji Deguchi, RA: 11202021349
Enzo Granato Adriano Cruz, RA: 11201920191
Lucas Yuji Simon, RA: 11201920811
Rafael Takeshi Ishikava, RA: 11201921451

## Uso
Projeto desenvolvido em Java 20

A partir da raiz do projeto:

__Gerar parsers:__  
`java -cp .:./lib/antlr-4.13.0-complete.jar org.antlr.v4.Tool ./GrammarExpression.g4 -package compiler.core -o ./src/compiler/core`

__Compilar main class:__  
`javac -cp ./lib/antlr-4.13.0-complete.jar -sourcepath ./src ./src/compiler/main/MainClass.java -d ./bin`

__Executar main class com input.isi, gerando arquivos java e c:__  
`java -cp bin:lib/antlr-4.13.0-complete.jar compiler.main.MainClass input.isi -java -c`  

__Para utilizar nomes de arquivo personalizados, colocar o nome do arquivo depois da flag da linguagem desejada:__  
`java -cp bin:lib/antlr-4.13.0-complete.jar compiler.main.MainClass input.isi -java JavaFile -c cFile`

## Itens implementados
__Itens Obrigatórios__
- Possui 2 tipos de variáveis
  - inteiros
  - reais
  - strings
- Possui a estrutura if...else
- Possui estrutura de controle while e for
- Operaçãoes Aritméticas executadas corretamente
  - Operações realizas de acordo com a ordem de precedência dos operadores
  - Não é possivel realizar operações entre diferentes tipos
- Atribuições realizadas corretamente
- Possui operações de Entrada e Saída
  - escreva()
  - leia()
- Aceita números decimais
- Verifica se a variável já foi previamente declarada
- Verifica se a variável foi declarada e não foi usada
- Verifica se uma variável está sendo usada sem ter valor inicial

__Itens Opcionais__
- [Editor Highlight](https://github.com/brunoseij/editor_texto_compiladores/)
- Avaliador de expressões aritméticas
  - Implementado pelas classes do pacote expressions, porém não utilizado complementamente visto que não foi desenvolvido um   interpretador. As expressões são avaliadas, porém resultados podem não condizer com o esperado por não seguir a estrutura de controle
- Inserção de Operadores lógicos
  - && e ||
- Geração de várias linguagens-alvo
  - Java
  - C

## Exemplo
```
programa
    integer a.
    real b.
    string c.

    a := 5.
    b := 6,5.
    c := "String".
    b := 7,5.

    se (a > 1 && b > 6,0) entao {
        escreva("a maior que 1").
    } senao {
        escreva("a menor que 1").
    }

    enquanto (b > 1,0 && a < 10) {
        a := a + 1.
    }

    para (b := 0,0. b < 5,0. b := b + 1,0) {
        escreva(b).
    }

    a := 1 + 2 - 3 * 4 / 5 + (6 + 7 * 8).
    b := 1,1 + 2,2 - 3,3 * 4,4 / 5,5 + (6,6 + 7,7 * 8,8).

    escreva(a).
    escreva(b).

    leia(c).
    escreva(c).
fimprog.
```

__Java__
```java
import java.util.Scanner;

public class example {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a;
            double b;
            String c;
            a = 5;
            b = 6.5;
            c = "String";
            b = 7.5;
            if (a>1&&b>6.0) {
                System.out.println("a maior que 1");
            }
            else {
                System.out.println("a menor que 1");
            }
            while (b>1.0&&a<10) {
                a = a+1;
            }
            for (b=0.0; b<5.0; b=b+1.0) {
                System.out.println(b);
            }
            a = 1+2-3*4/5+(6+7*8);
            b = 1.1+2.2-3.3*4.4/5.5+(6.6+7.7*8.8);
            System.out.println(a);
            System.out.println(b);
            c = scanner.nextLine();
            System.out.println(c);
        }
    }
}
```

__C__
```c
#include <string.h>
#include <stdio.h>

int main(void) {
    int a;
    float b;
    char c[256];
    a = 5;
    b = 6.5;
    strncpy(c, "String", sizeof(c));
    b = 7.5;
    if (a>1&&b>6.0) {
        printf("a maior que 1\n");
    }
    else {
        printf("a menor que 1\n");
    }
    while (b>1.0&&a<10) {
        a = a+1;
    }
    for (b=0.0; b<5.0; b=b+1.0) {
        printf("%f\n", b);
    }
    a = 1+2-3*4/5+(6+7*8);
    b = 1.1+2.2-3.3*4.4/5.5+(6.6+7.7*8.8);
    printf("%d\n", a);
    printf("%f\n", b);
    scanf("%s", c);
    printf("%s\n", c);
}
```
