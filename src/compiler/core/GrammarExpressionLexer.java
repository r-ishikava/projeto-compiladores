// Generated from ./GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;

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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, PROGRAM=4, ENDPROG=5, DECLARE=6, READ=7, WRITE=8, 
		IF=9, THEN=10, ELSE=11, FOR=12, WHILE=13, RELOP=14, TEXT=15, NUM=16, INT=17, 
		REAL=18, ID=19, COMMA=20, DOT=21, LPARENTHESIS=22, RPARENTHESIS=23, ASSIGN=24, 
		LCURLY=25, RCURLY=26, PLUS=27, MINUS=28, MULT=29, DIV=30, DQUOTE=31, WS=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "PROGRAM", "ENDPROG", "DECLARE", "READ", "WRITE", 
			"IF", "THEN", "ELSE", "FOR", "WHILE", "RELOP", "TEXT", "NUM", "INT", 
			"REAL", "ID", "COMMA", "DOT", "LPARENTHESIS", "RPARENTHESIS", "ASSIGN", 
			"LCURLY", "RCURLY", "PLUS", "MINUS", "MULT", "DIV", "DQUOTE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'integer'", "'real'", "'string'", "'programa'", "'fimprog'", "'declare'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'para'", "'enquanto'", 
			null, null, null, null, null, null, "','", "'.'", "'('", "')'", "':='", 
			"'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "PROGRAM", "ENDPROG", "DECLARE", "READ", "WRITE", 
			"IF", "THEN", "ELSE", "FOR", "WHILE", "RELOP", "TEXT", "NUM", "INT", 
			"REAL", "ID", "COMMA", "DOT", "LPARENTHESIS", "RPARENTHESIS", "ASSIGN", 
			"LCURLY", "RCURLY", "PLUS", "MINUS", "MULT", "DIV", "DQUOTE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	    private SymbolTable symbolTable = new SymbolTable();
	    private DataType currentType;
	    private DataType leftDT;
	    private DataType rightDT;
	    private StringBuilder expression;
	    private StringBuilder rawExpression;
	    private List<String> variablesList;
	    private String _lRelExp;
	    private String _rRelExp;
	    private String _relOp;
	    private String _forInit;
	    private String _forCondition;
	    private String _forIncrement;
	    private String _attribVariable;

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

	    /**
	     * Gets symbol by name, raises exception if variable is not declared
	     */
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

	    /**
	     * Gives warnings if declared variables are not used in expressions or the write command
	     */
	    public void unusedWarning() {
	        for (Symbol symbol : symbolTable.get_all()) {
	            if (!symbol.getUsed()) {
	                System.out.println("Warning: variable '" + symbol.getName() + "' was never used");
	            }
	        }
	    }


	public GrammarExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GrammarExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000 \u00e9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00a2"+
		"\b\r\u0001\u000e\u0001\u000e\u0005\u000e\u00a6\b\u000e\n\u000e\f\u000e"+
		"\u00a9\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u00af\b\u000f\u0001\u0010\u0004\u0010\u00b2\b\u0010\u000b\u0010\f\u0010"+
		"\u00b3\u0001\u0011\u0004\u0011\u00b7\b\u0011\u000b\u0011\f\u0011\u00b8"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u00bd\b\u0011\u000b\u0011\f\u0011"+
		"\u00be\u0001\u0012\u0003\u0012\u00c2\b\u0012\u0001\u0012\u0005\u0012\u00c5"+
		"\b\u0012\n\u0012\f\u0012\u00c8\t\u0012\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0004\u001f"+
		"\u00e4\b\u001f\u000b\u001f\f\u001f\u00e5\u0001\u001f\u0001\u001f\u0000"+
		"\u0000 \u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? \u0001"+
		"\u0000\u0006\u0002\u0000<<>>\u0006\u0000\t\t  \'\'09AZaz\u0001\u00000"+
		"9\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000\t\n\r\r  \u00f3\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000"+
		"\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000="+
		"\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0001A\u0001\u0000"+
		"\u0000\u0000\u0003I\u0001\u0000\u0000\u0000\u0005N\u0001\u0000\u0000\u0000"+
		"\u0007U\u0001\u0000\u0000\u0000\t^\u0001\u0000\u0000\u0000\u000bf\u0001"+
		"\u0000\u0000\u0000\rn\u0001\u0000\u0000\u0000\u000fs\u0001\u0000\u0000"+
		"\u0000\u0011{\u0001\u0000\u0000\u0000\u0013~\u0001\u0000\u0000\u0000\u0015"+
		"\u0084\u0001\u0000\u0000\u0000\u0017\u008a\u0001\u0000\u0000\u0000\u0019"+
		"\u008f\u0001\u0000\u0000\u0000\u001b\u00a1\u0001\u0000\u0000\u0000\u001d"+
		"\u00a3\u0001\u0000\u0000\u0000\u001f\u00ae\u0001\u0000\u0000\u0000!\u00b1"+
		"\u0001\u0000\u0000\u0000#\u00b6\u0001\u0000\u0000\u0000%\u00c1\u0001\u0000"+
		"\u0000\u0000\'\u00c9\u0001\u0000\u0000\u0000)\u00cb\u0001\u0000\u0000"+
		"\u0000+\u00cd\u0001\u0000\u0000\u0000-\u00cf\u0001\u0000\u0000\u0000/"+
		"\u00d1\u0001\u0000\u0000\u00001\u00d4\u0001\u0000\u0000\u00003\u00d6\u0001"+
		"\u0000\u0000\u00005\u00d8\u0001\u0000\u0000\u00007\u00da\u0001\u0000\u0000"+
		"\u00009\u00dc\u0001\u0000\u0000\u0000;\u00de\u0001\u0000\u0000\u0000="+
		"\u00e0\u0001\u0000\u0000\u0000?\u00e3\u0001\u0000\u0000\u0000AB\u0005"+
		"i\u0000\u0000BC\u0005n\u0000\u0000CD\u0005t\u0000\u0000DE\u0005e\u0000"+
		"\u0000EF\u0005g\u0000\u0000FG\u0005e\u0000\u0000GH\u0005r\u0000\u0000"+
		"H\u0002\u0001\u0000\u0000\u0000IJ\u0005r\u0000\u0000JK\u0005e\u0000\u0000"+
		"KL\u0005a\u0000\u0000LM\u0005l\u0000\u0000M\u0004\u0001\u0000\u0000\u0000"+
		"NO\u0005s\u0000\u0000OP\u0005t\u0000\u0000PQ\u0005r\u0000\u0000QR\u0005"+
		"i\u0000\u0000RS\u0005n\u0000\u0000ST\u0005g\u0000\u0000T\u0006\u0001\u0000"+
		"\u0000\u0000UV\u0005p\u0000\u0000VW\u0005r\u0000\u0000WX\u0005o\u0000"+
		"\u0000XY\u0005g\u0000\u0000YZ\u0005r\u0000\u0000Z[\u0005a\u0000\u0000"+
		"[\\\u0005m\u0000\u0000\\]\u0005a\u0000\u0000]\b\u0001\u0000\u0000\u0000"+
		"^_\u0005f\u0000\u0000_`\u0005i\u0000\u0000`a\u0005m\u0000\u0000ab\u0005"+
		"p\u0000\u0000bc\u0005r\u0000\u0000cd\u0005o\u0000\u0000de\u0005g\u0000"+
		"\u0000e\n\u0001\u0000\u0000\u0000fg\u0005d\u0000\u0000gh\u0005e\u0000"+
		"\u0000hi\u0005c\u0000\u0000ij\u0005l\u0000\u0000jk\u0005a\u0000\u0000"+
		"kl\u0005r\u0000\u0000lm\u0005e\u0000\u0000m\f\u0001\u0000\u0000\u0000"+
		"no\u0005l\u0000\u0000op\u0005e\u0000\u0000pq\u0005i\u0000\u0000qr\u0005"+
		"a\u0000\u0000r\u000e\u0001\u0000\u0000\u0000st\u0005e\u0000\u0000tu\u0005"+
		"s\u0000\u0000uv\u0005c\u0000\u0000vw\u0005r\u0000\u0000wx\u0005e\u0000"+
		"\u0000xy\u0005v\u0000\u0000yz\u0005a\u0000\u0000z\u0010\u0001\u0000\u0000"+
		"\u0000{|\u0005s\u0000\u0000|}\u0005e\u0000\u0000}\u0012\u0001\u0000\u0000"+
		"\u0000~\u007f\u0005e\u0000\u0000\u007f\u0080\u0005n\u0000\u0000\u0080"+
		"\u0081\u0005t\u0000\u0000\u0081\u0082\u0005a\u0000\u0000\u0082\u0083\u0005"+
		"o\u0000\u0000\u0083\u0014\u0001\u0000\u0000\u0000\u0084\u0085\u0005s\u0000"+
		"\u0000\u0085\u0086\u0005e\u0000\u0000\u0086\u0087\u0005n\u0000\u0000\u0087"+
		"\u0088\u0005a\u0000\u0000\u0088\u0089\u0005o\u0000\u0000\u0089\u0016\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0005p\u0000\u0000\u008b\u008c\u0005a\u0000"+
		"\u0000\u008c\u008d\u0005r\u0000\u0000\u008d\u008e\u0005a\u0000\u0000\u008e"+
		"\u0018\u0001\u0000\u0000\u0000\u008f\u0090\u0005e\u0000\u0000\u0090\u0091"+
		"\u0005n\u0000\u0000\u0091\u0092\u0005q\u0000\u0000\u0092\u0093\u0005u"+
		"\u0000\u0000\u0093\u0094\u0005a\u0000\u0000\u0094\u0095\u0005n\u0000\u0000"+
		"\u0095\u0096\u0005t\u0000\u0000\u0096\u0097\u0005o\u0000\u0000\u0097\u001a"+
		"\u0001\u0000\u0000\u0000\u0098\u00a2\u0007\u0000\u0000\u0000\u0099\u009a"+
		"\u0005<\u0000\u0000\u009a\u00a2\u0005=\u0000\u0000\u009b\u009c\u0005>"+
		"\u0000\u0000\u009c\u00a2\u0005=\u0000\u0000\u009d\u009e\u0005!\u0000\u0000"+
		"\u009e\u00a2\u0005=\u0000\u0000\u009f\u00a0\u0005=\u0000\u0000\u00a0\u00a2"+
		"\u0005=\u0000\u0000\u00a1\u0098\u0001\u0000\u0000\u0000\u00a1\u0099\u0001"+
		"\u0000\u0000\u0000\u00a1\u009b\u0001\u0000\u0000\u0000\u00a1\u009d\u0001"+
		"\u0000\u0000\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u001c\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a7\u0003=\u001e\u0000\u00a4\u00a6\u0007\u0001"+
		"\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0003=\u001e\u0000\u00ab\u001e\u0001\u0000\u0000"+
		"\u0000\u00ac\u00af\u0003!\u0010\u0000\u00ad\u00af\u0003#\u0011\u0000\u00ae"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af"+
		" \u0001\u0000\u0000\u0000\u00b0\u00b2\u0007\u0002\u0000\u0000\u00b1\u00b0"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\"\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b7\u0007\u0002\u0000\u0000\u00b6\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001"+
		"\u0000\u0000\u0000\u00ba\u00bc\u0003\'\u0013\u0000\u00bb\u00bd\u0007\u0002"+
		"\u0000\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000"+
		"\u0000\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000"+
		"\u0000\u0000\u00bf$\u0001\u0000\u0000\u0000\u00c0\u00c2\u0007\u0003\u0000"+
		"\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c5\u0007\u0004\u0000\u0000\u00c4\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7&\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005,\u0000\u0000\u00ca"+
		"(\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005.\u0000\u0000\u00cc*\u0001"+
		"\u0000\u0000\u0000\u00cd\u00ce\u0005(\u0000\u0000\u00ce,\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d0\u0005)\u0000\u0000\u00d0.\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u0005:\u0000\u0000\u00d2\u00d3\u0005=\u0000\u0000\u00d30\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d5\u0005{\u0000\u0000\u00d52\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d7\u0005}\u0000\u0000\u00d74\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d9\u0005+\u0000\u0000\u00d96\u0001\u0000\u0000\u0000\u00da\u00db\u0005"+
		"-\u0000\u0000\u00db8\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005*\u0000"+
		"\u0000\u00dd:\u0001\u0000\u0000\u0000\u00de\u00df\u0005/\u0000\u0000\u00df"+
		"<\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005\"\u0000\u0000\u00e1>\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e4\u0007\u0005\u0000\u0000\u00e3\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001"+
		"\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0006\u001f\u0000\u0000\u00e8@\u0001\u0000"+
		"\u0000\u0000\f\u0000\u00a1\u00a5\u00a7\u00ae\u00b3\u00b8\u00be\u00c1\u00c4"+
		"\u00c6\u00e5\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}