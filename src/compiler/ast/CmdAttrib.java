package compiler.ast;

import compiler.structures.Symbol;
import compiler.structures.DataType;

public class CmdAttrib extends AbstractCommand {
    private Symbol symbol;
    private String expression;

    public CmdAttrib(Symbol symbol, String expression) {
        super();
        this.symbol = symbol;
        this.expression = expression;
    }

    public CmdAttrib() {
        super();
    }

    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        if (symbol.getType() == DataType.STRING && this.expression.equals("")) {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append(symbol.getName() + " = " + "\"\";\n");
        } else {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append(symbol.getName() + " = " + expression.replace(",", ".") + ";\n");
        }
        return targetCode.toString();
    }

    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        if (symbol.getType() == DataType.STRING && this.expression.equals("")) {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append(symbol.getName() + " = " + "\"\";\n");
        } else {
            targetCode.append("\t".repeat(indentationLevel));
            targetCode.append(symbol.getName() + " = " + expression.replace(",", ".") + ";\n");
        }
        return targetCode.toString();
    }
}
