package compiler.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.core.GrammarExpressionLexer;
import compiler.core.GrammarExpressionParser;

public class MainClass {
    public static void main(String[] args) {
        Boolean debug = false;
        try {
            GrammarExpressionLexer lexer;
            GrammarExpressionParser parser;

            lexer = new GrammarExpressionLexer(CharStreams.fromFileName("input.expr"));
            
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            parser = new GrammarExpressionParser(tokenStream);

            if (debug) {
                CustomListener listener = new CustomListener();
                parser.addParseListener(listener);
                parser.addErrorListener(new CustomErrorListener());
            }

            parser.init();
            parser.programa();
            System.out.println("Compilation Success");
            parser.generateCTarget("c_code");
            parser.generateJavaTarget("JavaCode");
            if (debug) {
                parser.showSymbols();
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
