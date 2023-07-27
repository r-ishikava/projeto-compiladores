package compiler.ast;

import compiler.structures.Symbol;
import compiler.structures.DataType;

/**
 * Class for commands of the type "ID = expr.".
 * 
 * Ex: 
 * a := 1.
 * b := a + 1 - 2 * 3 / 4 + (a + 5).
 */
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

    /**
     * Ex:
     * a := "". -> a = "";
     * a := 1 + 2. -> a = 1 + 2;
     * a := "string". -> a = "string";
     */
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

    /**
     * Ex:
     * a := "". -> a = "";
     * a := 1 + 2. -> a = 1 + 2;
     * a := "string". -> a = "string";
     */
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
