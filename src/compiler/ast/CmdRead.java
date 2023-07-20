package compiler.ast;

import compiler.structures.Symbol;
import compiler.structures.DataType;

public class CmdRead extends AbstractCommand {
    private Symbol symbol;
    
    public CmdRead(Symbol symbol) {
        super();
        this.symbol = symbol;
    }

    public CmdRead() {
        super();
    }

    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        DataType dataType = symbol.getType();
        switch(dataType) {
            case INTEGER:
                targetCode.append("scanf(\"%d\", &" + symbol.getName() + ");\n");
                break;
            case REAL:
                targetCode.append("scanf(\"%f\", &" + symbol.getName() + ");\n");
                break;
            case STRING:
                targetCode.append("scanf(\"%s\", " + symbol.getName() + ");\n");
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        return targetCode.toString();
    }

    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        DataType dataType = symbol.getType();
        switch(dataType) {
            case INTEGER:
                targetCode.append(symbol.getName() + " = scanner.nextInt();\n");
                break;
            case REAL:
                targetCode.append(symbol.getName() + " = scanner.nextFloat();\n");
                break;
            case STRING:
                targetCode.append(symbol.getName() + " = scanner.nextLine();\n");
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        return targetCode.toString();
    }
}
