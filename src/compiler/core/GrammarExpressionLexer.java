// Generated from ./GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;
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
		PROGRAM=1, ENDPROG=2, DECLARE=3, READ=4, WRITE=5, IF=6, THEN=7, ELSE=8, 
		RELOP=9, TEXT=10, NUM=11, ID=12, COMMA=13, DOT=14, LPARENTHESIS=15, RPARENTHESIS=16, 
		ASSIGN=17, LCURLY=18, RCURLY=19, PLUS=20, MINUS=21, MULT=22, DIV=23, DQUOTE=24, 
		WS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PROGRAM", "ENDPROG", "DECLARE", "READ", "WRITE", "IF", "THEN", "ELSE", 
			"RELOP", "TEXT", "NUM", "ID", "COMMA", "DOT", "LPARENTHESIS", "RPARENTHESIS", 
			"ASSIGN", "LCURLY", "RCURLY", "PLUS", "MINUS", "MULT", "DIV", "DQUOTE", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog'", "'declare'", "'leia'", "'escreva'", 
			"'se'", "'entao'", "'senao'", null, null, null, null, "','", "'.'", "'('", 
			"')'", "':='", "'{'", "'}'", "'+'", "'-'", "'*'", "'/'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAM", "ENDPROG", "DECLARE", "READ", "WRITE", "IF", "THEN", 
			"ELSE", "RELOP", "TEXT", "NUM", "ID", "COMMA", "DOT", "LPARENTHESIS", 
			"RPARENTHESIS", "ASSIGN", "LCURLY", "RCURLY", "PLUS", "MINUS", "MULT", 
			"DIV", "DQUOTE", "WS"
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
		"\u0004\u0000\u0019\u00a9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\br\b\b\u0001\t\u0001\t\u0004\tv\b\t\u000b\t\f\tw\u0001"+
		"\t\u0001\t\u0001\n\u0004\n}\b\n\u000b\n\f\n~\u0001\u000b\u0003\u000b\u0082"+
		"\b\u000b\u0001\u000b\u0005\u000b\u0085\b\u000b\n\u000b\f\u000b\u0088\t"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0004\u0018\u00a4\b\u0018\u000b\u0018\f\u0018\u00a5\u0001"+
		"\u0018\u0001\u0018\u0000\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0006\u0002"+
		"\u0000<<>>\u0004\u0000  09AZaz\u0001\u000009\u0002\u0000AZaz\u0003\u0000"+
		"09AZaz\u0003\u0000\t\n\r\r  \u00b0\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000"+
		"-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001"+
		"\u0000\u0000\u0000\u00013\u0001\u0000\u0000\u0000\u0003<\u0001\u0000\u0000"+
		"\u0000\u0005D\u0001\u0000\u0000\u0000\u0007L\u0001\u0000\u0000\u0000\t"+
		"Q\u0001\u0000\u0000\u0000\u000bY\u0001\u0000\u0000\u0000\r\\\u0001\u0000"+
		"\u0000\u0000\u000fb\u0001\u0000\u0000\u0000\u0011q\u0001\u0000\u0000\u0000"+
		"\u0013s\u0001\u0000\u0000\u0000\u0015|\u0001\u0000\u0000\u0000\u0017\u0081"+
		"\u0001\u0000\u0000\u0000\u0019\u0089\u0001\u0000\u0000\u0000\u001b\u008b"+
		"\u0001\u0000\u0000\u0000\u001d\u008d\u0001\u0000\u0000\u0000\u001f\u008f"+
		"\u0001\u0000\u0000\u0000!\u0091\u0001\u0000\u0000\u0000#\u0094\u0001\u0000"+
		"\u0000\u0000%\u0096\u0001\u0000\u0000\u0000\'\u0098\u0001\u0000\u0000"+
		"\u0000)\u009a\u0001\u0000\u0000\u0000+\u009c\u0001\u0000\u0000\u0000-"+
		"\u009e\u0001\u0000\u0000\u0000/\u00a0\u0001\u0000\u0000\u00001\u00a3\u0001"+
		"\u0000\u0000\u000034\u0005p\u0000\u000045\u0005r\u0000\u000056\u0005o"+
		"\u0000\u000067\u0005g\u0000\u000078\u0005r\u0000\u000089\u0005a\u0000"+
		"\u00009:\u0005m\u0000\u0000:;\u0005a\u0000\u0000;\u0002\u0001\u0000\u0000"+
		"\u0000<=\u0005f\u0000\u0000=>\u0005i\u0000\u0000>?\u0005m\u0000\u0000"+
		"?@\u0005p\u0000\u0000@A\u0005r\u0000\u0000AB\u0005o\u0000\u0000BC\u0005"+
		"g\u0000\u0000C\u0004\u0001\u0000\u0000\u0000DE\u0005d\u0000\u0000EF\u0005"+
		"e\u0000\u0000FG\u0005c\u0000\u0000GH\u0005l\u0000\u0000HI\u0005a\u0000"+
		"\u0000IJ\u0005r\u0000\u0000JK\u0005e\u0000\u0000K\u0006\u0001\u0000\u0000"+
		"\u0000LM\u0005l\u0000\u0000MN\u0005e\u0000\u0000NO\u0005i\u0000\u0000"+
		"OP\u0005a\u0000\u0000P\b\u0001\u0000\u0000\u0000QR\u0005e\u0000\u0000"+
		"RS\u0005s\u0000\u0000ST\u0005c\u0000\u0000TU\u0005r\u0000\u0000UV\u0005"+
		"e\u0000\u0000VW\u0005v\u0000\u0000WX\u0005a\u0000\u0000X\n\u0001\u0000"+
		"\u0000\u0000YZ\u0005s\u0000\u0000Z[\u0005e\u0000\u0000[\f\u0001\u0000"+
		"\u0000\u0000\\]\u0005e\u0000\u0000]^\u0005n\u0000\u0000^_\u0005t\u0000"+
		"\u0000_`\u0005a\u0000\u0000`a\u0005o\u0000\u0000a\u000e\u0001\u0000\u0000"+
		"\u0000bc\u0005s\u0000\u0000cd\u0005e\u0000\u0000de\u0005n\u0000\u0000"+
		"ef\u0005a\u0000\u0000fg\u0005o\u0000\u0000g\u0010\u0001\u0000\u0000\u0000"+
		"hr\u0007\u0000\u0000\u0000ij\u0005<\u0000\u0000jr\u0005=\u0000\u0000k"+
		"l\u0005>\u0000\u0000lr\u0005=\u0000\u0000mn\u0005!\u0000\u0000nr\u0005"+
		"=\u0000\u0000op\u0005=\u0000\u0000pr\u0005=\u0000\u0000qh\u0001\u0000"+
		"\u0000\u0000qi\u0001\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000qm\u0001"+
		"\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000r\u0012\u0001\u0000\u0000"+
		"\u0000su\u0003/\u0017\u0000tv\u0007\u0001\u0000\u0000ut\u0001\u0000\u0000"+
		"\u0000vw\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0003/\u0017\u0000z\u0014\u0001"+
		"\u0000\u0000\u0000{}\u0007\u0002\u0000\u0000|{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000"+
		"\u0000\u0000\u007f\u0016\u0001\u0000\u0000\u0000\u0080\u0082\u0007\u0003"+
		"\u0000\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0086\u0001\u0000"+
		"\u0000\u0000\u0083\u0085\u0007\u0004\u0000\u0000\u0084\u0083\u0001\u0000"+
		"\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0018\u0001\u0000"+
		"\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008a\u0005,\u0000"+
		"\u0000\u008a\u001a\u0001\u0000\u0000\u0000\u008b\u008c\u0005.\u0000\u0000"+
		"\u008c\u001c\u0001\u0000\u0000\u0000\u008d\u008e\u0005(\u0000\u0000\u008e"+
		"\u001e\u0001\u0000\u0000\u0000\u008f\u0090\u0005)\u0000\u0000\u0090 \u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0005:\u0000\u0000\u0092\u0093\u0005=\u0000"+
		"\u0000\u0093\"\u0001\u0000\u0000\u0000\u0094\u0095\u0005{\u0000\u0000"+
		"\u0095$\u0001\u0000\u0000\u0000\u0096\u0097\u0005}\u0000\u0000\u0097&"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0005+\u0000\u0000\u0099(\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0005-\u0000\u0000\u009b*\u0001\u0000\u0000\u0000"+
		"\u009c\u009d\u0005*\u0000\u0000\u009d,\u0001\u0000\u0000\u0000\u009e\u009f"+
		"\u0005/\u0000\u0000\u009f.\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\""+
		"\u0000\u0000\u00a10\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0005\u0000"+
		"\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0006\u0018\u0000"+
		"\u0000\u00a82\u0001\u0000\u0000\u0000\t\u0000quw~\u0081\u0084\u0086\u00a5"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}