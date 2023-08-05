package compiler.main;

import java.io.File;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import compiler.core.GrammarExpressionLexer;
import compiler.core.GrammarExpressionParser;

/**
 * Compiler main class.
 *
 * Usage: source_file.isi [-java [java file name]] [-c [c file name]] [-debug]
 *
 * source_file.isi: A file with the extension .isi, the source file to be compiled.
 * -java: Generate java code.
 * -c: Generate c code.
 * -debug: Print the parser rules as the file is parsed and the symbol table.
 *
 *  Raises an exception when no arguments are given, the first argument is not a .isi file or the file doesn't exist.
 *  If no target language arguments are given, it will only check if the file can be successfully compiled.
 *  If no target language file names are given, it will use the source file name.
 */
public class MainClass {
    private static Boolean debug = false;;
    private static Boolean java = false;;
    private static Boolean c = false;
    private static String javaFilename;
    private static String cFilename;

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No source file found");
        } else if (!args[0].endsWith(".isi")) {
            throw new RuntimeException("First argument must be the .isi source file");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new RuntimeException("File '" + args[0] + "' not found");
        }
        String filename = file.getName().substring(0, file.getName().length() - 4);
        javaFilename = cFilename = filename;
        parseArgs(args);
        compile(file.getPath());
    }

    private static void parseArgs(String[] args) {
        for (int i=1; i<args.length; i++) {
            String argument = args[i];
            switch (argument) {
                case "-java":
                    java = true;
                    javaFilename = i + 1 < args.length && !args[i+1].startsWith("-") ? args[i+1] : javaFilename;
                    break;
                case "-c":
                    c = true;
                    cFilename = i + 1 < args.length && !args[i+1].startsWith("-") ? args[i+1] : cFilename;
                    break;
                case "-debug":
                    debug = true;
                    break;
                default:
                    if (argument.startsWith("-")) {
                        System.out.println("Invalid argument '" + argument + "'");
                        System.exit(1);
                    }
                    break;
            }
        }
    }

    private static void compile(String filepath) {
        try {
            GrammarExpressionLexer lexer;
            GrammarExpressionParser parser;

            lexer = new GrammarExpressionLexer(CharStreams.fromFileName(filepath));
            
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            parser = new GrammarExpressionParser(tokenStream);

            if (debug) {
                CustomListener listener = new CustomListener();
                parser.addParseListener(listener);
                parser.addErrorListener(new CustomErrorListener());
            }

            parser.init();
            parser.programa();
            if (c) {
                parser.generateCTarget(cFilename);
            }
            if (java) {
                parser.generateJavaTarget(javaFilename);
            }
            if (debug) {
                parser.showSymbols();
            }
            System.out.println("Compilation Success");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
