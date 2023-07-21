package compiler.ast;

import java.util.List;

public class CmdWhile extends AbstractCommand {
    private List<AbstractCommand> cmdList;
    private String expression;

    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("while (" + expression + ") {\n");
        for (AbstractCommand cmd : cmdList) {
            targetCode.append(cmd.generateCCode());
        }
        targetCode.append("}\n");
        return targetCode.toString();
    }

    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("while (" + expression + ") {\n");
        for (AbstractCommand cmd : cmdList) {
            targetCode.append(cmd.generateJavaCode());
        }
        targetCode.append("}\n");
        return targetCode.toString();
    }

    public List<AbstractCommand> getCmdList() {
        return cmdList;
    }

    public void setCmdList(List<AbstractCommand> cmdList) {
        this.cmdList = cmdList;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
