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
    import compiler.expressions.ArithmeticExpression;
    import compiler.expressions.RelationalExpression;
    import compiler.ast.*;

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
		IF=9, THEN=10, ELSE=11, FOR=12, WHILE=13, RELOP=14, TEXT=15, NUM=16, INT=17, 
		REAL=18, ID=19, COMMA=20, DOT=21, LPARENTHESIS=22, RPARENTHESIS=23, ASSIGN=24, 
		LCURLY=25, RCURLY=26, PLUS=27, MINUS=28, MULT=29, DIV=30, DQUOTE=31, WS=32;
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
	    private DataType leftDT;
	    private DataType rightDT;
	    private StringBuilder expression;
<<<<<<< HEAD
	    private StringBuilder rawExpression;
=======
	    private ArithmeticExpression arithmeticExpression;
	    private RelationalExpression relationalExpression;
>>>>>>> b4aa16d (Expressions rework, possible breakages)
	    private List<String> variablesList;
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

			                   program.setCommands(stack.pop());
			                   unusedWarning();
			               
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
			setState(47);
			type();
			 variablesList = new ArrayList<>(); 
			setState(49);
			varlist();
			setState(50);
			match(DOT);

			                   CmdDeclare _declare = new CmdDeclare(currentType, variablesList);
			                   stack.peek().add(_declare);
			               
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
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(T__0);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				match(T__1);
				 currentType = DataType.REAL; 
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
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
			setState(61);
			match(ID);

			                   symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
			                   variablesList.add(_input.LT(-1).getText());
			               
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(63);
				match(COMMA);
				setState(64);
				match(ID);

				                       symbolTable.add_symbol(new Symbol(_input.LT(-1).getText(), currentType));
				                       variablesList.add(_input.LT(-1).getText());
				                   
				}
				}
				setState(70);
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
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				cmd();
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 537472L) != 0) );
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
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdexprContext cmdexpr() {
			return getRuleContext(CmdexprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
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
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case READ:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				cmdleitura();
				}
				break;
			case WRITE:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				cmdexpr();
				setState(79);
				match(DOT);
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				cmdif();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(82);
				cmdfor();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 6);
				{
				setState(83);
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
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
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
			setState(86);
			match(READ);
			setState(87);
			match(LPARENTHESIS);
			setState(88);
			match(ID);

			                   Symbol symbol = getCheckedSymbol(_input.LT(-1).getText()); 
			                   //Set variable value to 0 to not trigger the uninitialized error
			                   symbol.setValue("0");
			                   CmdRead _read = new CmdRead(symbol);
			                   stack.peek().add(_read);
			               
			setState(90);
			match(RPARENTHESIS);
			setState(91);
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
	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(GrammarExpressionParser.WRITE, 0); }
		public TerminalNode LPARENTHESIS() { return getToken(GrammarExpressionParser.LPARENTHESIS, 0); }
		public TerminalNode RPARENTHESIS() { return getToken(GrammarExpressionParser.RPARENTHESIS, 0); }
		public TerminalNode DOT() { return getToken(GrammarExpressionParser.DOT, 0); }
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
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXT:
				{
				setState(95);
				match(TEXT);

				                       CmdWrite _write  = new CmdWrite(_input.LT(-1).getText());
				                       stack.peek().add(_write);
				                   
				}
				break;
			case ID:
				{
				setState(97);
				match(ID);

				                       Symbol symbol = getCheckedSymbol(_input.LT(-1).getText());
				                       if (symbol.getValue() == null) {
				                           throw new SemanticException("Trying to read from an uninitialized variable: '" + symbol.getName() + "'");
				                       }
				                       CmdWrite _write = new CmdWrite(symbol);
				                       stack.peek().add(_write);
				                       symbol.setUsed(true);
				                   
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(101);
			match(RPARENTHESIS);
			setState(102);
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
	public static class CmdexprContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(GrammarExpressionParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(GrammarExpressionParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(GrammarExpressionParser.TEXT, 0); }
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
			setState(104);
			match(ID);

			                  Symbol assigned_variable = getCheckedSymbol(_input.LT(-1).getText());
			                  leftDT = assigned_variable.getType();
			                  rightDT = null;
			                  _attribVariable = _input.LT(-1).getText();
			               
<<<<<<< HEAD
			 
			                   expression = new StringBuilder();
			                   rawExpression = new StringBuilder();
			               
			setState(107);
=======
			setState(106);
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			match(ASSIGN);
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
			case ID:
			case LPARENTHESIS:
				{
				setState(107);
				expr();

				                       if (leftDT != DataType.INTEGER && leftDT != DataType.REAL) {
				                           throw new SemanticException("Can not assign to string variables through expressions");
				                       }
				                       PostfixExpression postfixExpression = ExpressionConverter.infixToPostfix(expression.toString());
				                       String result = postfixExpression.calculate().replace('.', ',');
				                       assigned_variable.setValue(result);
				                       symbolTable.add_symbol(assigned_variable);
				                   
				}
				break;
			case TEXT:
				{
				setState(110);
				match(TEXT);

				                       if (leftDT != DataType.STRING) {
				                           throw new SemanticException("Tried to assign a string value to a " + leftDT + " variable");
				                       }
				                       assigned_variable.setValue(_input.LT(-1).getText());
				                       symbolTable.add_symbol(assigned_variable);
				                   
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

<<<<<<< HEAD
			                   CmdAttrib _attrib = new CmdAttrib(assigned_variable, rawExpression.toString().replace(",", "."));
=======
			                CmdAttrib _attrib;
			                if (expression == null) {
			                    _attrib = new CmdAttrib(assigned_variable, "");
			                } else {
			                   _attrib = new CmdAttrib(assigned_variable, expression.toString().replace(",", "."));
			                }
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			                   stack.peek().add(_attrib);
			                expression = null;
			               
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
			setState(116);
			match(IF);

			                   stack.push(new ArrayList<AbstractCommand>());
			                   CmdIf _if = new CmdIf();
			               
			setState(118);
			match(LPARENTHESIS);
			setState(119);
			relexpr();
			setState(120);
			match(RPARENTHESIS);
			setState(121);
			match(THEN);
			setState(122);
			match(LCURLY);
			setState(123);
			bloco();

			                   _if.setTrueList(stack.pop());
			               
			setState(125);
			match(RCURLY);
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(126);
				match(ELSE);

				                       stack.push(new ArrayList<AbstractCommand>());
				                   
				setState(128);
				match(LCURLY);
				setState(129);
				bloco();
				setState(130);
				match(RCURLY);

				                       _if.setFalseList(stack.pop());
				                   
				}
			}

			 
			                   _if.setExpression(relationalExpression.toString());
			                   stack.peek().add(_if);
			               
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
			setState(137);
			match(FOR);

			                   stack.push(new ArrayList<AbstractCommand>());
			                   CmdFor _for = new CmdFor();
			               
			setState(139);
			match(LPARENTHESIS);
			setState(140);
			cmdexpr();

			                   stack.peek().remove(stack.peek().size() - 1);
<<<<<<< HEAD
			                   _forInit = _attribVariable + "=" + rawExpression.toString().replace(",", ".");
=======
			                   _forInit = _attribVariable + "=" + arithmeticExpression.toString().replace(",", ".");
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			               
			setState(142);
			match(DOT);
			setState(143);
			relexpr();

			                   _forCondition = relationalExpression.getLeftSide().toString() +
			                        relationalExpression.getOperator() +
			                        relationalExpression.getRightSide().toString();
			               
			setState(145);
			match(DOT);
			setState(146);
			cmdexpr();

			                   stack.peek().remove(stack.peek().size() - 1);
<<<<<<< HEAD
			                   _forIncrement = _attribVariable + "=" + rawExpression.toString().replace(",", ".");
=======
			                   _forIncrement = _attribVariable + "=" + arithmeticExpression.toString().replace(",", ".");
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			               
			setState(148);
			match(RPARENTHESIS);
			setState(149);
			match(LCURLY);
			setState(150);
			bloco();
			setState(151);
			match(RCURLY);

			                   _for.setCmdList(stack.pop());
			                   _for.setInit(_forInit);
			                   _for.setCondition(_forCondition);
			                   _for.setIncrement(_forIncrement);
			                   stack.peek().add(_for);
			               
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
			setState(154);
			match(WHILE);

			                  stack.push(new ArrayList<AbstractCommand>());
			                  CmdWhile _while = new CmdWhile();
			               
			setState(156);
			match(LPARENTHESIS);
			setState(157);
			relexpr();
			setState(158);
			match(RPARENTHESIS);
			setState(159);
			match(LCURLY);
			setState(160);
			bloco();

			                   _while.setCmdList(stack.pop());
			               
			setState(162);
			match(RCURLY);

			                   _while.setExpression(relationalExpression.toString());
			                   stack.peek().add(_while);
			               
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
<<<<<<< HEAD
			 rawExpression = new StringBuilder(); 
			setState(167);
			expr();

			                   _lRelExp = rawExpression.toString().replace(",", ".");
=======
			 relationalExpression = new RelationalExpression(); 
			setState(166);
			expr();

			                   relationalExpression.setLeftSide(arithmeticExpression);
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			               
			 expression = null; 
			setState(169);
			match(RELOP);

			                   relationalExpression.setOperator(_input.LT(-1).getText());
			               
<<<<<<< HEAD
			 rawExpression = new StringBuilder(); 
			setState(172);
			expr();

			                   _rRelExp = rawExpression.toString().replace(",", ".");
=======
			setState(171);
			expr();

			                   relationalExpression.setRightSide(arithmeticExpression);
>>>>>>> b4aa16d (Expressions rework, possible breakages)
			               
			 expression = null; 
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

			                   if (expression == null) {
			                       expression = new StringBuilder();
			                   }
			               
			setState(176);
			termo();
			setState(177);
			exprl();
			 arithmeticExpression = new ArithmeticExpression(expression.toString());
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
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(GrammarExpressionParser.PLUS, 0); }
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
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                   expression.append(_input.LT(-1).getText());
				                   rawExpression.append(_input.LT(-1).getText());
				               
				setState(182);
				termo();
				setState(183);
				exprl();
				}
				break;
			case RELOP:
			case DOT:
			case RPARENTHESIS:
				enterOuterAlt(_localctx, 2);
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
			setState(188);
			fator();
			setState(189);
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
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TermolContext termol() {
			return getRuleContext(TermolContext.class,0);
		}
		public TerminalNode MULT() { return getToken(GrammarExpressionParser.MULT, 0); }
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
		int _la;
		try {
			setState(197);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MULT:
			case DIV:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				_la = _input.LA(1);
				if ( !(_la==MULT || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				                   expression.append(_input.LT(-1).getText());
				                   rawExpression.append(_input.LT(-1).getText());
				               
				setState(193);
				fator();
				setState(194);
				termol();
				}
				break;
			case RELOP:
			case DOT:
			case RPARENTHESIS:
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 2);
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
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(NUM);

				                   String number = _input.LT(-1).getText();
				                   if (number.contains(String.valueOf(',')) && leftDT != DataType.REAL) {
				                       throw new SemanticException("REAL value in a " + leftDT + " type expression");
				                   } else if (!number.contains(String.valueOf(',')) && leftDT != DataType.INTEGER) {
				                       throw new SemanticException("INTEGER value in a " + leftDT + " type expression");
				                   }
				                   expression.append(_input.LT(-1).getText());
				                   rawExpression.append(_input.LT(-1).getText());
				               
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				match(ID);

				                   Symbol operand = getCheckedSymbol(_input.LT(-1).getText());
				                   if (operand.getType() != leftDT) {
				                       throw new SemanticException("Variable of the " + operand.getType() + " type in a " + leftDT + " type expression");
				                   }
				                   if (operand.getValue() == null) {
				                       throw new SemanticException("Use of uninitialized variable '" + operand.getName() + "' of the type " + operand.getType());
				                   }
				                   expression.append(operand.getValue());
				                   rawExpression.append(operand.getName());
				                   operand.setUsed(true);
				               
				}
				break;
			case LPARENTHESIS:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				match(LPARENTHESIS);

				                   expression.append('(');
				                   rawExpression.append('(');
				               
				setState(205);
				expr();
				setState(206);
				match(RPARENTHESIS);

				                   expression.append(')');
				                   rawExpression.append(')');
				               
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
		"\u0004\u0001 \u00d4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0001\u0000\u0001\u0000"+
		"\u0004\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002<\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003C\b\u0003\n\u0003"+
		"\f\u0003F\t\u0003\u0001\u0004\u0004\u0004I\b\u0004\u000b\u0004\f\u0004"+
		"J\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005U\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"d\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bq\b\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u0086\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00bb"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00c6\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00d2\b\u0011\u0001"+
		"\u0011\u0000\u0000\u0012\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"\u0000\u0002\u0001\u0000\u001b"+
		"\u001c\u0001\u0000\u001d\u001e\u00d2\u0000$\u0001\u0000\u0000\u0000\u0002"+
		"/\u0001\u0000\u0000\u0000\u0004;\u0001\u0000\u0000\u0000\u0006=\u0001"+
		"\u0000\u0000\u0000\bH\u0001\u0000\u0000\u0000\nT\u0001\u0000\u0000\u0000"+
		"\fV\u0001\u0000\u0000\u0000\u000e]\u0001\u0000\u0000\u0000\u0010h\u0001"+
		"\u0000\u0000\u0000\u0012t\u0001\u0000\u0000\u0000\u0014\u0089\u0001\u0000"+
		"\u0000\u0000\u0016\u009a\u0001\u0000\u0000\u0000\u0018\u00a5\u0001\u0000"+
		"\u0000\u0000\u001a\u00af\u0001\u0000\u0000\u0000\u001c\u00ba\u0001\u0000"+
		"\u0000\u0000\u001e\u00bc\u0001\u0000\u0000\u0000 \u00c5\u0001\u0000\u0000"+
		"\u0000\"\u00d1\u0001\u0000\u0000\u0000$&\u0005\u0004\u0000\u0000%\'\u0003"+
		"\u0002\u0001\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000"+
		"(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000"+
		"\u0000*+\u0003\b\u0004\u0000+,\u0005\u0005\u0000\u0000,-\u0005\u0015\u0000"+
		"\u0000-.\u0006\u0000\uffff\uffff\u0000.\u0001\u0001\u0000\u0000\u0000"+
		"/0\u0003\u0004\u0002\u000001\u0006\u0001\uffff\uffff\u000012\u0003\u0006"+
		"\u0003\u000023\u0005\u0015\u0000\u000034\u0006\u0001\uffff\uffff\u0000"+
		"4\u0003\u0001\u0000\u0000\u000056\u0005\u0001\u0000\u00006<\u0006\u0002"+
		"\uffff\uffff\u000078\u0005\u0002\u0000\u00008<\u0006\u0002\uffff\uffff"+
		"\u00009:\u0005\u0003\u0000\u0000:<\u0006\u0002\uffff\uffff\u0000;5\u0001"+
		"\u0000\u0000\u0000;7\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000"+
		"<\u0005\u0001\u0000\u0000\u0000=>\u0005\u0013\u0000\u0000>D\u0006\u0003"+
		"\uffff\uffff\u0000?@\u0005\u0014\u0000\u0000@A\u0005\u0013\u0000\u0000"+
		"AC\u0006\u0003\uffff\uffff\u0000B?\u0001\u0000\u0000\u0000CF\u0001\u0000"+
		"\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000E\u0007"+
		"\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000GI\u0003\n\u0005\u0000"+
		"HG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000JK\u0001\u0000\u0000\u0000K\t\u0001\u0000\u0000\u0000LU\u0003\f"+
		"\u0006\u0000MU\u0003\u000e\u0007\u0000NO\u0003\u0010\b\u0000OP\u0005\u0015"+
		"\u0000\u0000PU\u0001\u0000\u0000\u0000QU\u0003\u0012\t\u0000RU\u0003\u0014"+
		"\n\u0000SU\u0003\u0016\u000b\u0000TL\u0001\u0000\u0000\u0000TM\u0001\u0000"+
		"\u0000\u0000TN\u0001\u0000\u0000\u0000TQ\u0001\u0000\u0000\u0000TR\u0001"+
		"\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000U\u000b\u0001\u0000\u0000"+
		"\u0000VW\u0005\u0007\u0000\u0000WX\u0005\u0016\u0000\u0000XY\u0005\u0013"+
		"\u0000\u0000YZ\u0006\u0006\uffff\uffff\u0000Z[\u0005\u0017\u0000\u0000"+
		"[\\\u0005\u0015\u0000\u0000\\\r\u0001\u0000\u0000\u0000]^\u0005\b\u0000"+
		"\u0000^c\u0005\u0016\u0000\u0000_`\u0005\u000f\u0000\u0000`d\u0006\u0007"+
		"\uffff\uffff\u0000ab\u0005\u0013\u0000\u0000bd\u0006\u0007\uffff\uffff"+
		"\u0000c_\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000de\u0001\u0000"+
		"\u0000\u0000ef\u0005\u0017\u0000\u0000fg\u0005\u0015\u0000\u0000g\u000f"+
		"\u0001\u0000\u0000\u0000hi\u0005\u0013\u0000\u0000ij\u0006\b\uffff\uffff"+
		"\u0000jp\u0005\u0018\u0000\u0000kl\u0003\u001a\r\u0000lm\u0006\b\uffff"+
		"\uffff\u0000mq\u0001\u0000\u0000\u0000no\u0005\u000f\u0000\u0000oq\u0006"+
		"\b\uffff\uffff\u0000pk\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000"+
		"qr\u0001\u0000\u0000\u0000rs\u0006\b\uffff\uffff\u0000s\u0011\u0001\u0000"+
		"\u0000\u0000tu\u0005\t\u0000\u0000uv\u0006\t\uffff\uffff\u0000vw\u0005"+
		"\u0016\u0000\u0000wx\u0003\u0018\f\u0000xy\u0005\u0017\u0000\u0000yz\u0005"+
		"\n\u0000\u0000z{\u0005\u0019\u0000\u0000{|\u0003\b\u0004\u0000|}\u0006"+
		"\t\uffff\uffff\u0000}\u0085\u0005\u001a\u0000\u0000~\u007f\u0005\u000b"+
		"\u0000\u0000\u007f\u0080\u0006\t\uffff\uffff\u0000\u0080\u0081\u0005\u0019"+
		"\u0000\u0000\u0081\u0082\u0003\b\u0004\u0000\u0082\u0083\u0005\u001a\u0000"+
		"\u0000\u0083\u0084\u0006\t\uffff\uffff\u0000\u0084\u0086\u0001\u0000\u0000"+
		"\u0000\u0085~\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0006\t\uffff\uffff\u0000"+
		"\u0088\u0013\u0001\u0000\u0000\u0000\u0089\u008a\u0005\f\u0000\u0000\u008a"+
		"\u008b\u0006\n\uffff\uffff\u0000\u008b\u008c\u0005\u0016\u0000\u0000\u008c"+
		"\u008d\u0003\u0010\b\u0000\u008d\u008e\u0006\n\uffff\uffff\u0000\u008e"+
		"\u008f\u0005\u0015\u0000\u0000\u008f\u0090\u0003\u0018\f\u0000\u0090\u0091"+
		"\u0006\n\uffff\uffff\u0000\u0091\u0092\u0005\u0015\u0000\u0000\u0092\u0093"+
		"\u0003\u0010\b\u0000\u0093\u0094\u0006\n\uffff\uffff\u0000\u0094\u0095"+
		"\u0005\u0017\u0000\u0000\u0095\u0096\u0005\u0019\u0000\u0000\u0096\u0097"+
		"\u0003\b\u0004\u0000\u0097\u0098\u0005\u001a\u0000\u0000\u0098\u0099\u0006"+
		"\n\uffff\uffff\u0000\u0099\u0015\u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"\r\u0000\u0000\u009b\u009c\u0006\u000b\uffff\uffff\u0000\u009c\u009d\u0005"+
		"\u0016\u0000\u0000\u009d\u009e\u0003\u0018\f\u0000\u009e\u009f\u0005\u0017"+
		"\u0000\u0000\u009f\u00a0\u0005\u0019\u0000\u0000\u00a0\u00a1\u0003\b\u0004"+
		"\u0000\u00a1\u00a2\u0006\u000b\uffff\uffff\u0000\u00a2\u00a3\u0005\u001a"+
		"\u0000\u0000\u00a3\u00a4\u0006\u000b\uffff\uffff\u0000\u00a4\u0017\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0006\f\uffff\uffff\u0000\u00a6\u00a7\u0003"+
		"\u001a\r\u0000\u00a7\u00a8\u0006\f\uffff\uffff\u0000\u00a8\u00a9\u0006"+
		"\f\uffff\uffff\u0000\u00a9\u00aa\u0005\u000e\u0000\u0000\u00aa\u00ab\u0006"+
		"\f\uffff\uffff\u0000\u00ab\u00ac\u0003\u001a\r\u0000\u00ac\u00ad\u0006"+
		"\f\uffff\uffff\u0000\u00ad\u00ae\u0006\f\uffff\uffff\u0000\u00ae\u0019"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0006\r\uffff\uffff\u0000\u00b0\u00b1"+
		"\u0003\u001e\u000f\u0000\u00b1\u00b2\u0003\u001c\u000e\u0000\u00b2\u00b3"+
		"\u0006\r\uffff\uffff\u0000\u00b3\u001b\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0007\u0000\u0000\u0000\u00b5\u00b6\u0006\u000e\uffff\uffff\u0000\u00b6"+
		"\u00b7\u0003\u001e\u000f\u0000\u00b7\u00b8\u0003\u001c\u000e\u0000\u00b8"+
		"\u00bb\u0001\u0000\u0000\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba"+
		"\u00b4\u0001\u0000\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb"+
		"\u001d\u0001\u0000\u0000\u0000\u00bc\u00bd\u0003\"\u0011\u0000\u00bd\u00be"+
		"\u0003 \u0010\u0000\u00be\u001f\u0001\u0000\u0000\u0000\u00bf\u00c0\u0007"+
		"\u0001\u0000\u0000\u00c0\u00c1\u0006\u0010\uffff\uffff\u0000\u00c1\u00c2"+
		"\u0003\"\u0011\u0000\u00c2\u00c3\u0003 \u0010\u0000\u00c3\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c4\u00c6\u0001\u0000\u0000\u0000\u00c5\u00bf\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6!\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c8\u0005\u0010\u0000\u0000\u00c8\u00d2\u0006\u0011"+
		"\uffff\uffff\u0000\u00c9\u00ca\u0005\u0013\u0000\u0000\u00ca\u00d2\u0006"+
		"\u0011\uffff\uffff\u0000\u00cb\u00cc\u0005\u0016\u0000\u0000\u00cc\u00cd"+
		"\u0006\u0011\uffff\uffff\u0000\u00cd\u00ce\u0003\u001a\r\u0000\u00ce\u00cf"+
		"\u0005\u0017\u0000\u0000\u00cf\u00d0\u0006\u0011\uffff\uffff\u0000\u00d0"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d1\u00c7\u0001\u0000\u0000\u0000\u00d1"+
		"\u00c9\u0001\u0000\u0000\u0000\u00d1\u00cb\u0001\u0000\u0000\u0000\u00d2"+
		"#\u0001\u0000\u0000\u0000\u000b(;DJTcp\u0085\u00ba\u00c5\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}