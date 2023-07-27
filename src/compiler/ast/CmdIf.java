package compiler.ast;

import java.util.List;

/**
 * Class for commands of the type "se (relexpr) { commands }".
 */
public class CmdIf extends AbstractCommand {
    /**
     * Command list for the if block.
     */
    private List<AbstractCommand> trueList;
    /**
     * Command list for the else block.
     */
    private List<AbstractCommand> falseList;
    private String expression;

    public CmdIf() {
        super();
    }

    /**
     * Ex:
     * se (a < b) entao {
     *     commands
     * } senao {
     *     commands
     * }
     * ->
     * if (a<b) {
     *     commands
     * }
     * else {
     *     commands
     * }
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("if (" + expression + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : trueList) {
            targetCode.append(cmd.generateCCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        if (falseList != null) {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append("else {\n");
            indentationLevel++;
            for (AbstractCommand cmd : falseList) {
                targetCode.append(cmd.generateCCode());
            }
            indentationLevel--;
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append("}\n");
        }
        return targetCode.toString();
    }

    /**
     * Ex:
     * se (a < b) entao {
     *     commands
     * } senao {
     *     commands
     * }
     * ->
     * if (a<b) {
     *     commands
     * }
     * else {
     *     commands
     * }
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("if (" + expression + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : trueList) {
            targetCode.append(cmd.generateJavaCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        if (falseList != null) {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append("else {\n");
            indentationLevel++;
            for (AbstractCommand cmd : falseList) {
                targetCode.append(cmd.generateJavaCode());
            }
            indentationLevel--;
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append("}\n");
        }
        return targetCode.toString();
    }

    public List<AbstractCommand> getTrueList() {
        return trueList;
    }

    public void setTrueList(List<AbstractCommand> trueList) {
        this.trueList = trueList;
    }

    public List<AbstractCommand> getFalseList() {
        return falseList;
    }

    public void setFalseList(List<AbstractCommand> falseList) {
        this.falseList = falseList;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
