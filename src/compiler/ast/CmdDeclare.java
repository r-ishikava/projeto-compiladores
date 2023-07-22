package compiler.ast;

import java.util.List;
import compiler.structures.DataType;

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
