package compiler.ast;

import java.util.List;

public class CmdIf extends AbstractCommand {
    private List<AbstractCommand> trueList;
    private List<AbstractCommand> falseList;
    private String expression;

    public CmdIf() {
        super();
    }

    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("if (" + expression + ") {\n");
        for (AbstractCommand cmd : trueList) {
            targetCode.append(cmd.generateCCode());
        }
        targetCode.append("}\n");
        if (falseList != null) {
            targetCode.append("else {\n");
            for (AbstractCommand cmd : falseList) {
                targetCode.append(cmd.generateCCode());
            }
            targetCode.append("}\n");
        }
        return targetCode.toString();
    }

    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("if (" + expression + ") {\n");
        for (AbstractCommand cmd : trueList) {
            targetCode.append(cmd.generateJavaCode());
        }
        targetCode.append("}\n");
        if (falseList != null) {
            targetCode.append("else {\n");
            for (AbstractCommand cmd : falseList) {
                targetCode.append(cmd.generateJavaCode());
            }
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
