package compiler.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.core.GrammarExpressionLexer;
import compiler.core.GrammarExpressionParser;

public class MainClass {
    public static void main(String[] args) {
        try {
            GrammarExpressionLexer lexer;
            GrammarExpressionParser parser;

            lexer = new GrammarExpressionLexer(CharStreams.fromFileName("input.expr"));
            
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            CustomListener listener = new CustomListener();

            parser = new GrammarExpressionParser(tokenStream);
            parser.addParseListener(listener);
            parser.addErrorListener(new CustomErrorListener());

            parser.programa();
            System.out.println("Compilation Success");
            parser.showSymbols();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
