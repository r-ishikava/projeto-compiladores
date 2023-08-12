package compiler.ast;

import java.util.List;
import compiler.expressions.Expression;

/**
 * Class for commands of the type "para (ID := expr. relexpr. ID := expr) { commands }".
 */
public class CmdFor extends AbstractCommand {
    private String init;
    private Expression condition;
    private String increment;
    private List<AbstractCommand> cmdList;

    public CmdFor() {
        super();
    }

    /**
     * Ex:
     * para (i := 0. i < 10. i = i + 1) {
     *     commands
     * }
     * ->
     * for (i=0; i<10; i=i+1) {
     *     commands
     * }
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("for (" + this.init + "; " + this.condition.toString().replace(",", ".") + "; " + this.increment + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : this.cmdList) {
            targetCode.append(cmd.generateCCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        return targetCode.toString();
    }

    /**
     * Ex:
     * para (i := 0. i < 10. i = i + 1) {
     *     commands
     * }
     * ->
     * for (i=0; i<10; i=i+1) {
     *     commands
     * }
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("for (" + this.init + "; " + this.condition.toString().replace(",", ".") + "; " + this.increment + ") {\n");
        indentationLevel++;
        for (AbstractCommand cmd : this.cmdList) {
            targetCode.append(cmd.generateJavaCode());
        }
        indentationLevel--;
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append("}\n");
        return targetCode.toString();
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    public List<AbstractCommand> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<AbstractCommand> cmdList) {
        this.cmdList = cmdList;
    }
}
