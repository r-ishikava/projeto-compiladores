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

            if (args.length != 0) {
                lexer = new GrammarExpressionLexer(CharStreams.fromString(args[0]));
            } else {
                lexer = new GrammarExpressionLexer(CharStreams.fromFileName("input.expr"));
            }
            
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            parser = new GrammarExpressionParser(tokenStream);

            if (args.length == 0) {
                CustomListener listener = new CustomListener();
                parser.addParseListener(listener);
                parser.addErrorListener(new CustomErrorListener());
            }

            parser.programa();
            System.out.println("Compilation Success");
            parser.showSymbols();
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
