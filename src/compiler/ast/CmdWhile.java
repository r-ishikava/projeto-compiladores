package compiler.ast;

import java.util.List;
import compiler.expressions.Expression;

/**
 * Class for commands of the type "enquanto (relexpr) { commands }".
 */
public class CmdWhile extends AbstractCommand {
    private List<AbstractCommand> cmdList;
    private Expression expression;

    /**
     * Ex:
     * equanto(a < b) {
     *     commands
     * }
     * ->
     * while (a<b) {
     *     commands
     * }
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("while (" + expression.toString().replace(",", ".") + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : cmdList) {
            targetCode.append(cmd.generateCCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        return targetCode.toString();
    }

    /**
     * Ex:
     * equanto (a < b) {
     *     commands
     * }
     * ->
     * while (a<b) {
     *     commands
     * }
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("while (" + expression.toString().replace(",", ".") + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : cmdList) {
            targetCode.append(cmd.generateJavaCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        return targetCode.toString();
    }

    public List<AbstractCommand> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<AbstractCommand> cmdList) {
        this.cmdList = cmdList;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
