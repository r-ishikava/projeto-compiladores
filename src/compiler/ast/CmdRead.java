package compiler.ast;

import compiler.structures.Symbol;
import compiler.structures.DataType;

/**
 * Class for commands of the type "leia(ID).".
 */
public class CmdRead extends AbstractCommand {
    private Symbol symbol;
    
    public CmdRead(Symbol symbol) {
        super();
        this.symbol = symbol;
    }

    public CmdRead() {
        super();
    }

    /**
     * Ex:
     * int a.
     * real b.
     * string c.
     * leia(a). -> scanf("%d", &a);
     * leia(b). -> scanf("%f", &b);
     * leia(c). -> scanf("%s", c);
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        DataType dataType = symbol.getType();
        switch(dataType) {
            case INTEGER:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append("scanf(\"%d\", &" + symbol.getName() + ");\n");
                break;
            case REAL:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append("scanf(\"%f\", &" + symbol.getName() + ");\n");
                break;
            case STRING:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append("scanf(\"%s\", " + symbol.getName() + ");\n");
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        return targetCode.toString();
    }

    /**
     * Ex:
     * int a.
     * real b.
     * string c.
     * leia(a). -> a = scanner.nextInt();
     * leia(b). -> b = scanner.nextFloat();
     * leia(c). -> c = scanner.nextLine();
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        DataType dataType = symbol.getType();
        switch(dataType) {
            case INTEGER:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append(symbol.getName() + " = scanner.nextInt();\n");
                break;
            case REAL:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append(symbol.getName() + " = scanner.nextFloat();\n");
                break;
            case STRING:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append(symbol.getName() + " = scanner.nextLine();\n");
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        return targetCode.toString();
    }
}
