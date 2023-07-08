package compiler.main;

import compiler.core.GrammarExpressionBaseListener;
import compiler.core.GrammarExpressionParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CustomListener extends GrammarExpressionBaseListener {
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        String rule_name = GrammarExpressionParser.ruleNames[ctx.getRuleIndex()];
        System.out.println("Entering rule: " + rule_name);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        System.out.println("Terminal: " + node.getText());
    }
}
