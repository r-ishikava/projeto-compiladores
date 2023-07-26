package compiler.ast;

import compiler.structures.Symbol;
import compiler.structures.DataType;

/**
 * Class for commands of the type "leia(ID or TEXT).";
 */
public class CmdWrite extends AbstractCommand {
    private Symbol symbol;
    private Boolean rawStringFlag;

    public CmdWrite(Symbol symbol) {
        super();
        this.symbol = symbol;
        this.rawStringFlag = false;
    }

    /**
     * If a string is passed as a parameter, create a temporary symbol.
     */
    public CmdWrite(String text) {
        super();
        this.symbol = new Symbol("TMPSTRING", DataType.STRING);
        this.symbol.setValue(text);
        this.rawStringFlag = true;
    }

    public CmdWrite() {
        super();
    }

    /**
     * Ex:
     * int a.
     * real b.
     * string c.
     * leia(a). -> printf("%d\n", a);
     * leia(b). -> printf("%f\n", b);
     * leia(c). -> printf("%s\n", c);
     * leia("String"). -> printf("String\n");
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        DataType dataType = symbol.getType();
        switch(dataType) {
            case INTEGER:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append("printf(\"%d\\n\", " + symbol.getName() +  ");\n");
                break;
            case REAL:
                targetCode.append("\t".repeat(indentationLevel));
                targetCode.append("printf(\"%f\\n\", " + symbol.getName() +  ");\n");
                break;
            case STRING:
                targetCode.append("\t".repeat(indentationLevel));
                if (rawStringFlag) {
                    //Remove quotes
                    String text = symbol.getValue().substring(1, symbol.getValue().length() - 1);
                    targetCode.append("printf(\"" + text + "\\n\");\n");
                } else {
                    targetCode.append("printf(\"%s\\n\"," + symbol.getName() +  ");\n");
                }
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        return targetCode.toString();
    }

    /**
     * Ex:
     * leia(a). -> System.out.println(a);
     * leia("String"). -> System.out.println("String");
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        targetCode.append("\t".repeat(indentationLevel));
        if (rawStringFlag) {
            targetCode.append(String.format("System.out.println(%s);\n", symbol.getValue()));
        } else {
            targetCode.append(String.format("System.out.println(%s);\n", symbol.getName()));
        }
        return targetCode.toString();
    }
}
