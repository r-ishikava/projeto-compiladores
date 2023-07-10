// Generated from ./GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;

    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.structures.DataType;
    import compiler.exceptions.SemanticException;
    import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class GrammarExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, PROGRAM=4, ENDPROG=5, DECLARE=6, READ=7, WRITE=8, 
		IF=9, THEN=10, ELSE=11, FOR=12, WHILE=13, RELOP=14, TEXT=15, NUM=16, ID=17, 
		COMMA=18, DOT=19, LPARENTHESIS=20, RPARENTHESIS=21, ASSIGN=22, LCURLY=23, 
		RCURLY=24, PLUS=25, MINUS=26, MULT=27, DIV=28, DQUOTE=29, WS=30;
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_type = 2, RULE_varlist = 3, 
		RULE_bloco = 4, RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, 
		RULE_cmdexpr = 8, RULE_cmdif = 9, RULE_cmdfor = 10, RULE_cmdwhile = 11, 
		RULE_relexpr = 12, RULE_expr = 13, RULE_exprl = 14, RULE_termo = 15, RULE_termol = 16, 
		RULE_fator = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declara", "type", "varlist", "bloco", "cmd", "cmdleitura", 
			"cmdescrita", "cmdexpr", "cmdif", "cmdfor", "cmdwhile", "relexpr", "expr", 
			"exprl", "termo", "termol", "fator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'float'", "'string'", "'programa'", "'fimprog'", "'declare'", 
			"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'for'", "'while'", 
			null, null, null, null, "','", "'.'", "'('", "')'", "':='", "'{'", "'}'", 
			"'+'", "'-'", "'*'", "'/'", "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "PROGRAM", "ENDPROG", "DECLARE", "READ", "WRITE", 
			"IF", "THEN", "ELSE", "FOR", "WHILE", "RELOP", "TEXT", "NUM", "ID", "COMMA", 
			"DOT", "LPARENTHESIS", "RPARENTHESIS", "ASSIGN", "LCURLY", "RCURLY", 
			"PLUS", "MINUS", "MULT", "DIV", "DQUOTE", "WS"
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

	@Override
	public String getGrammarFileName() { return "GrammarExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public GrammarExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(GrammarExpressionParser.PROGRAM, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode ENDPROG() { return getToken(GrammarExpressionParser.ENDPROG, 0); }
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
		public List<DeclaraContext> declara() {
			return getRuleContexts(DeclaraContext.class);
		}
		public DeclaraContext declara(int i) {
			return getRuleContext(DeclaraContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(PROGRAM);
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				declara();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0) );
			setState(42);
			bloco();
			setState(43);
			match(ENDPROG);
			setState(44);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarlistContext varlist() {
			return getRuleContext(VarlistContext.class,0);
		}
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			type();
			setState(47);
			varlist();
			setState(48);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__0);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__1);
				 currentType = DataType.FLOAT; 
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				match(T__2);
				 currentType = DataType.STRING; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarlistContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(GrammarExpressionParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(GrammarExpressionParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GrammarExpressionParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GrammarExpressionParser.COMMA, i);
		}
		public VarlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterVarlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitVarlist(this);
		}
	}

	public final VarlistContext varlist() throws RecognitionException {
		VarlistContext _localctx = new VarlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ID);
			 symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType)); 
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(60);
				match(COMMA);
				setState(61);
				match(ID);
				 symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType)); 
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				cmd();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 144256L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdexprContext cmdexpr() {
			return getRuleContext(CmdexprContext.class,0);
		}
		public CmdifContext cmdif() {
			return getRuleContext(CmdifContext.class,0);
		}
		public CmdforContext cmdfor() {
			return getRuleContext(CmdforContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case READ:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				cmdleitura();
				setState(74);
				match(DOT);
				}
				break;
			case WRITE:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				cmdescrita();
				setState(77);
				match(DOT);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				cmdexpr();
				setState(80);
				match(DOT);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(82);
				cmdif();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				cmdfor();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 6);
				{
				setState(84);
				cmdwhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(GrammarExpressionParser.READ, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(READ);
			setState(88);
			match(LPARENTHESIS);
			setState(89);
			match(ID);
			 verifyDeclaration(_input.LT(-1).getText()); 
			setState(91);
			match(RPARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(GrammarExpressionParser.WRITE, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public TerminalNode TEXT() { return getToken(GrammarExpressionParser.TEXT, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(WRITE);
			setState(94);
			match(LPARENTHESIS);
			setState(98);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT:
				{
				setState(95);
				match(TEXT);
				}
				break;
			case ID:
				{
				setState(96);
				match(ID);
				 verifyDeclaration(_input.LT(-1).getText()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(100);
			match(RPARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdexprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(GrammarExpressionParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CmdexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdexpr(this);
		}
	}

	public final CmdexprContext cmdexpr() throws RecognitionException {
		CmdexprContext _localctx = new CmdexprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdexpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(ID);
			 verifyDeclaration(_input.LT(-1).getText()); 
			setState(104);
			match(ASSIGN);
			setState(105);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdifContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(GrammarExpressionParser.IF, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public RelexprContext relexpr() {
			return getRuleContext(RelexprContext.class,0);
		}
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public TerminalNode THEN() { return getToken(GrammarExpressionParser.THEN, 0); }
		public List<TerminalNode> LCURLY() { return getTokens(GrammarExpressionParser.LCURLY); }
		public TerminalNode LCURLY(int i) {
			return getToken(GrammarExpressionParser.LCURLY, i);
		}
		public List<BlocoContext> bloco() {
			return getRuleContexts(BlocoContext.class);
		}
		public BlocoContext bloco(int i) {
			return getRuleContext(BlocoContext.class,i);
		}
		public List<TerminalNode> RCURLY() { return getTokens(GrammarExpressionParser.RCURLY); }
		public TerminalNode RCURLY(int i) {
			return getToken(GrammarExpressionParser.RCURLY, i);
		}
		public TerminalNode ELSE() { return getToken(GrammarExpressionParser.ELSE, 0); }
		public CmdifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdif(this);
		}
	}

	public final CmdifContext cmdif() throws RecognitionException {
		CmdifContext _localctx = new CmdifContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(IF);
			setState(108);
			match(LPARENTHESIS);
			setState(109);
			relexpr();
			setState(110);
			match(RPARENTHESIS);
			setState(111);
			match(THEN);
			setState(112);
			match(LCURLY);
			setState(113);
			bloco();
			setState(114);
			match(RCURLY);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(115);
				match(ELSE);
				setState(116);
				match(LCURLY);
				setState(117);
				bloco();
				setState(118);
				match(RCURLY);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdforContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(GrammarExpressionParser.FOR, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public List<CmdexprContext> cmdexpr() {
			return getRuleContexts(CmdexprContext.class);
		}
		public CmdexprContext cmdexpr(int i) {
			return getRuleContext(CmdexprContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(GrammarExpressionParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(GrammarExpressionParser.DOT, i);
		}
		public RelexprContext relexpr() {
			return getRuleContext(RelexprContext.class,0);
		}
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public TerminalNode LCURLY() { return getToken(GrammarExpressionParser.LCURLY, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(GrammarExpressionParser.RCURLY, 0); }
		public CmdforContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdfor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdfor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdfor(this);
		}
	}

	public final CmdforContext cmdfor() throws RecognitionException {
		CmdforContext _localctx = new CmdforContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdfor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(FOR);
			setState(123);
			match(LPARENTHESIS);
			setState(124);
			cmdexpr();
			setState(125);
			match(DOT);
			setState(126);
			relexpr();
			setState(127);
			match(DOT);
			setState(128);
			cmdexpr();
			setState(129);
			match(RPARENTHESIS);
			setState(130);
			match(LCURLY);
			setState(131);
			bloco();
			setState(132);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdwhileContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(GrammarExpressionParser.WHILE, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public RelexprContext relexpr() {
			return getRuleContext(RelexprContext.class,0);
		}
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public TerminalNode LCURLY() { return getToken(GrammarExpressionParser.LCURLY, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode RCURLY() { return getToken(GrammarExpressionParser.RCURLY, 0); }
		public CmdwhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterCmdwhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitCmdwhile(this);
		}
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdwhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(WHILE);
			setState(135);
			match(LPARENTHESIS);
			setState(136);
			relexpr();
			setState(137);
			match(RPARENTHESIS);
			setState(138);
			match(LCURLY);
			setState(139);
			bloco();
			setState(140);
			match(RCURLY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelexprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RELOP() { return getToken(GrammarExpressionParser.RELOP, 0); }
		public RelexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterRelexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitRelexpr(this);
		}
	}

	public final RelexprContext relexpr() throws RecognitionException {
		RelexprContext _localctx = new RelexprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_relexpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			expr();
			setState(143);
			match(RELOP);
			setState(144);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			termo();
			setState(147);
			exprl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(GrammarExpressionParser.PLUS, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(GrammarExpressionParser.MINUS, 0); }
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprl);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				match(PLUS);
				setState(150);
				termo();
				setState(151);
				exprl();
				}
				break;
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(MINUS);
				setState(154);
				termo();
				setState(155);
				exprl();
				}
				break;
			case RELOP:
			case DOT:
			case RPARENTHESIS:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TermolContext termol() {
			return getRuleContext(TermolContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			fator();
			setState(161);
			termol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermolContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(GrammarExpressionParser.MULT, 0); }
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TermolContext termol() {
			return getRuleContext(TermolContext.class,0);
		}
		public TerminalNode DIV() { return getToken(GrammarExpressionParser.DIV, 0); }
		public TermolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterTermol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitTermol(this);
		}
	}

	public final TermolContext termol() throws RecognitionException {
		TermolContext _localctx = new TermolContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termol);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				match(MULT);
				setState(164);
				fator();
				setState(165);
				termol();
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(167);
				match(DIV);
				setState(168);
				fator();
				setState(169);
				termol();
				}
				break;
			case RELOP:
			case DOT:
			case RPARENTHESIS:
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(GrammarExpressionParser.NUM, 0); }
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarExpressionListener ) ((GrammarExpressionListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fator);
		try {
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(NUM);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(ID);
				 verifyDeclaration(_input.LT(-1).getText()); 
				}
				break;
			case LPARENTHESIS:
				enterOuterAlt(_localctx, 3);
				{
				setState(177);
				match(LPARENTHESIS);
				setState(178);
				expr();
				setState(179);
				match(RPARENTHESIS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u00b8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001"+
		"\u0000\u0004\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u00029\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003@\b\u0003\n\u0003\f\u0003C\t\u0003\u0001\u0004"+
		"\u0004\u0004F\b\u0004\u000b\u0004\f\u0004G\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007c\b"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\ty\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u009f\b\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00ad\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00b6\b\u0011\u0001\u0011"+
		"\u0000\u0000\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"\u0000\u0000\u00b7\u0000$\u0001\u0000"+
		"\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u00048\u0001\u0000\u0000\u0000"+
		"\u0006:\u0001\u0000\u0000\u0000\bE\u0001\u0000\u0000\u0000\nU\u0001\u0000"+
		"\u0000\u0000\fW\u0001\u0000\u0000\u0000\u000e]\u0001\u0000\u0000\u0000"+
		"\u0010f\u0001\u0000\u0000\u0000\u0012k\u0001\u0000\u0000\u0000\u0014z"+
		"\u0001\u0000\u0000\u0000\u0016\u0086\u0001\u0000\u0000\u0000\u0018\u008e"+
		"\u0001\u0000\u0000\u0000\u001a\u0092\u0001\u0000\u0000\u0000\u001c\u009e"+
		"\u0001\u0000\u0000\u0000\u001e\u00a0\u0001\u0000\u0000\u0000 \u00ac\u0001"+
		"\u0000\u0000\u0000\"\u00b5\u0001\u0000\u0000\u0000$&\u0005\u0004\u0000"+
		"\u0000%\'\u0003\u0002\u0001\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*+\u0003\b\u0004\u0000+,\u0005\u0005\u0000\u0000,-\u0005"+
		"\u0013\u0000\u0000-\u0001\u0001\u0000\u0000\u0000./\u0003\u0004\u0002"+
		"\u0000/0\u0003\u0006\u0003\u000001\u0005\u0013\u0000\u00001\u0003\u0001"+
		"\u0000\u0000\u000023\u0005\u0001\u0000\u000039\u0006\u0002\uffff\uffff"+
		"\u000045\u0005\u0002\u0000\u000059\u0006\u0002\uffff\uffff\u000067\u0005"+
		"\u0003\u0000\u000079\u0006\u0002\uffff\uffff\u000082\u0001\u0000\u0000"+
		"\u000084\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u00009\u0005\u0001"+
		"\u0000\u0000\u0000:;\u0005\u0011\u0000\u0000;A\u0006\u0003\uffff\uffff"+
		"\u0000<=\u0005\u0012\u0000\u0000=>\u0005\u0011\u0000\u0000>@\u0006\u0003"+
		"\uffff\uffff\u0000?<\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000\u0000"+
		"A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\u0007\u0001\u0000"+
		"\u0000\u0000CA\u0001\u0000\u0000\u0000DF\u0003\n\u0005\u0000ED\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000H\t\u0001\u0000\u0000\u0000IJ\u0003\f\u0006\u0000JK"+
		"\u0005\u0013\u0000\u0000KV\u0001\u0000\u0000\u0000LM\u0003\u000e\u0007"+
		"\u0000MN\u0005\u0013\u0000\u0000NV\u0001\u0000\u0000\u0000OP\u0003\u0010"+
		"\b\u0000PQ\u0005\u0013\u0000\u0000QV\u0001\u0000\u0000\u0000RV\u0003\u0012"+
		"\t\u0000SV\u0003\u0014\n\u0000TV\u0003\u0016\u000b\u0000UI\u0001\u0000"+
		"\u0000\u0000UL\u0001\u0000\u0000\u0000UO\u0001\u0000\u0000\u0000UR\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UT\u0001\u0000\u0000\u0000"+
		"V\u000b\u0001\u0000\u0000\u0000WX\u0005\u0007\u0000\u0000XY\u0005\u0014"+
		"\u0000\u0000YZ\u0005\u0011\u0000\u0000Z[\u0006\u0006\uffff\uffff\u0000"+
		"[\\\u0005\u0015\u0000\u0000\\\r\u0001\u0000\u0000\u0000]^\u0005\b\u0000"+
		"\u0000^b\u0005\u0014\u0000\u0000_c\u0005\u000f\u0000\u0000`a\u0005\u0011"+
		"\u0000\u0000ac\u0006\u0007\uffff\uffff\u0000b_\u0001\u0000\u0000\u0000"+
		"b`\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0005\u0015\u0000"+
		"\u0000e\u000f\u0001\u0000\u0000\u0000fg\u0005\u0011\u0000\u0000gh\u0006"+
		"\b\uffff\uffff\u0000hi\u0005\u0016\u0000\u0000ij\u0003\u001a\r\u0000j"+
		"\u0011\u0001\u0000\u0000\u0000kl\u0005\t\u0000\u0000lm\u0005\u0014\u0000"+
		"\u0000mn\u0003\u0018\f\u0000no\u0005\u0015\u0000\u0000op\u0005\n\u0000"+
		"\u0000pq\u0005\u0017\u0000\u0000qr\u0003\b\u0004\u0000rx\u0005\u0018\u0000"+
		"\u0000st\u0005\u000b\u0000\u0000tu\u0005\u0017\u0000\u0000uv\u0003\b\u0004"+
		"\u0000vw\u0005\u0018\u0000\u0000wy\u0001\u0000\u0000\u0000xs\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000y\u0013\u0001\u0000\u0000\u0000"+
		"z{\u0005\f\u0000\u0000{|\u0005\u0014\u0000\u0000|}\u0003\u0010\b\u0000"+
		"}~\u0005\u0013\u0000\u0000~\u007f\u0003\u0018\f\u0000\u007f\u0080\u0005"+
		"\u0013\u0000\u0000\u0080\u0081\u0003\u0010\b\u0000\u0081\u0082\u0005\u0015"+
		"\u0000\u0000\u0082\u0083\u0005\u0017\u0000\u0000\u0083\u0084\u0003\b\u0004"+
		"\u0000\u0084\u0085\u0005\u0018\u0000\u0000\u0085\u0015\u0001\u0000\u0000"+
		"\u0000\u0086\u0087\u0005\r\u0000\u0000\u0087\u0088\u0005\u0014\u0000\u0000"+
		"\u0088\u0089\u0003\u0018\f\u0000\u0089\u008a\u0005\u0015\u0000\u0000\u008a"+
		"\u008b\u0005\u0017\u0000\u0000\u008b\u008c\u0003\b\u0004\u0000\u008c\u008d"+
		"\u0005\u0018\u0000\u0000\u008d\u0017\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0003\u001a\r\u0000\u008f\u0090\u0005\u000e\u0000\u0000\u0090\u0091\u0003"+
		"\u001a\r\u0000\u0091\u0019\u0001\u0000\u0000\u0000\u0092\u0093\u0003\u001e"+
		"\u000f\u0000\u0093\u0094\u0003\u001c\u000e\u0000\u0094\u001b\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0005\u0019\u0000\u0000\u0096\u0097\u0003\u001e"+
		"\u000f\u0000\u0097\u0098\u0003\u001c\u000e\u0000\u0098\u009f\u0001\u0000"+
		"\u0000\u0000\u0099\u009a\u0005\u001a\u0000\u0000\u009a\u009b\u0003\u001e"+
		"\u000f\u0000\u009b\u009c\u0003\u001c\u000e\u0000\u009c\u009f\u0001\u0000"+
		"\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u0095\u0001\u0000"+
		"\u0000\u0000\u009e\u0099\u0001\u0000\u0000\u0000\u009e\u009d\u0001\u0000"+
		"\u0000\u0000\u009f\u001d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0003\"\u0011"+
		"\u0000\u00a1\u00a2\u0003 \u0010\u0000\u00a2\u001f\u0001\u0000\u0000\u0000"+
		"\u00a3\u00a4\u0005\u001b\u0000\u0000\u00a4\u00a5\u0003\"\u0011\u0000\u00a5"+
		"\u00a6\u0003 \u0010\u0000\u00a6\u00ad\u0001\u0000\u0000\u0000\u00a7\u00a8"+
		"\u0005\u001c\u0000\u0000\u00a8\u00a9\u0003\"\u0011\u0000\u00a9\u00aa\u0003"+
		" \u0010\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00ad\u0001\u0000"+
		"\u0000\u0000\u00ac\u00a3\u0001\u0000\u0000\u0000\u00ac\u00a7\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad!\u0001\u0000\u0000"+
		"\u0000\u00ae\u00b6\u0005\u0010\u0000\u0000\u00af\u00b0\u0005\u0011\u0000"+
		"\u0000\u00b0\u00b6\u0006\u0011\uffff\uffff\u0000\u00b1\u00b2\u0005\u0014"+
		"\u0000\u0000\u00b2\u00b3\u0003\u001a\r\u0000\u00b3\u00b4\u0005\u0015\u0000"+
		"\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b5\u00af\u0001\u0000\u0000\u0000\u00b5\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b6#\u0001\u0000\u0000\u0000\n(8AGUbx\u009e\u00ac\u00b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}