package compiler.ast;

import java.util.List;
import compiler.structures.DataType;

/**
 * Class for commands of the type "type a, b, c.".
 */
public class CmdDeclare extends AbstractCommand {
    private DataType type;
    private List<String> variablesList;

    public CmdDeclare(DataType type, List<String> variablesList) {
        super();
        this.type = type;
        this.variablesList = variablesList;
    }

    public CmdDeclare() {
        super();
    }

    /**
     * Ex:
     * int a, b, c. -> int a, b, c;
     * real a, b, c. -> float a, b, c;
     * string a, b, c -> char* a, b, c;
     */
    @Override
    public String generateCCode() {
        StringBuilder targetCode = new StringBuilder();
        String variablesType;
        switch (type) {
            case INTEGER:
                variablesType = "int";
                break;
            case REAL:
                variablesType = "float";
                break;
            case STRING:
                variablesType = "char*";
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append(variablesType + " " + String.join(", ", variablesList) + ";\n");
        return targetCode.toString();
    }

    /**
     * Ex:
     * int a, b, c. -> int a, b, c;
     * real a, b, c. -> double a, b, c;
     * string a, b, c -> String a, b, c;
     */
    @Override
    public String generateJavaCode() {
        StringBuilder targetCode = new StringBuilder();
        String variablesType;
        switch (type) {
            case INTEGER:
                variablesType = "int";
                break;
            case REAL:
                variablesType = "double";
                break;
            case STRING:
                variablesType = "String";
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
        targetCode.append("\t".repeat(indentationLevel));
        targetCode.append(variablesType + " " + String.join(", ", variablesList) + ";\n");
        return targetCode.toString();
    }
}
