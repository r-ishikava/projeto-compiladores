// Generated from ./GrammarExpression.g4 by ANTLR 4.13.0
package compiler.core;

    import compiler.structures.Symbol;
    import compiler.structures.SymbolTable;
    import compiler.structures.DataType;
    import compiler.exceptions.SemanticException;
    import java.util.ArrayList;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarExpressionParser}.
 */
public interface GrammarExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(GrammarExpressionParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(GrammarExpressionParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(GrammarExpressionParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(GrammarExpressionParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GrammarExpressionParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GrammarExpressionParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#varlist}.
	 * @param ctx the parse tree
	 */
	void enterVarlist(GrammarExpressionParser.VarlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#varlist}.
	 * @param ctx the parse tree
	 */
	void exitVarlist(GrammarExpressionParser.VarlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(GrammarExpressionParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(GrammarExpressionParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(GrammarExpressionParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(GrammarExpressionParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(GrammarExpressionParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(GrammarExpressionParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void enterCmdexpr(GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdexpr}.
	 * @param ctx the parse tree
	 */
	void exitCmdexpr(GrammarExpressionParser.CmdexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void enterCmdif(GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void exitCmdif(GrammarExpressionParser.CmdifContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdfor}.
	 * @param ctx the parse tree
	 */
	void enterCmdfor(GrammarExpressionParser.CmdforContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdfor}.
	 * @param ctx the parse tree
	 */
	void exitCmdfor(GrammarExpressionParser.CmdforContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdwhile(GrammarExpressionParser.CmdwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdwhile(GrammarExpressionParser.CmdwhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#relexpr}.
	 * @param ctx the parse tree
	 */
	void enterRelexpr(GrammarExpressionParser.RelexprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#relexpr}.
	 * @param ctx the parse tree
	 */
	void exitRelexpr(GrammarExpressionParser.RelexprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GrammarExpressionParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GrammarExpressionParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(GrammarExpressionParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(GrammarExpressionParser.ExprlContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(GrammarExpressionParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(GrammarExpressionParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#termol}.
	 * @param ctx the parse tree
	 */
	void enterTermol(GrammarExpressionParser.TermolContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#termol}.
	 * @param ctx the parse tree
	 */
	void exitTermol(GrammarExpressionParser.TermolContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(GrammarExpressionParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarExpressionParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(GrammarExpressionParser.FatorContext ctx);
}