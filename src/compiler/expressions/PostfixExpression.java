package compiler.expressions;

import java.util.Stack;
import compiler.structures.SymbolTable;
import compiler.structures.Symbol;

/**
 * Class to handle expressions in the postfix notation.
 */
public class PostfixExpression extends Expression {
    private Stack<String> stack;
    private SymbolTable symbolTable;
    /**
     * Each element must be separated by a whitespace.
     */
    private String expression;

    protected PostfixExpression(String expression, SymbolTable symbolTable) {
        super();
        this.expression = expression;
        this.stack = new Stack<>();
        this.symbolTable = symbolTable;
    }

    /**
     * Returns the string representation of the expression.
     */
    @Override
    public String toString() {
        return expression;
    }

    /**
     * Uses the stack algorithm to calculate the result.
     * If there is at least one float value, it will use the java widening cast for the subsequent operands and the result.
     */
    @Override
    public String calculate() {
        Boolean floatFlag = false;
        String[] expressionElements = expression.split(" ");
        for (String element : expressionElements) {
            Symbol symbol = symbolTable.get_symbol(element);
            if (symbol != null) {
                element = symbol.getValue();
            }
            element = element.replace(",", ".");
            if (isInteger(element) || isFloat(element)) {
                stack.push(element);
            } else {
                Number res;
                String a = stack.pop();
                String b = stack.pop();
                if (a.contains(String.valueOf('.')) || b.contains(String.valueOf('.'))) {
                    floatFlag = true;
                }
                if (element.equals("+")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) + Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) + Integer.parseInt(a);
                    }
                } else if (element.equals("-")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) - Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) - Integer.parseInt(a);
                    }
                } else if (element.equals("*")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) * Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) * Integer.parseInt(a);
                    }
                } else if (element.equals("/")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) / Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) / Integer.parseInt(a);
                    }
                } else {
                    throw new RuntimeException("Unrecognized operator '" + element + "' found in the expression");
                }
                stack.push(res.toString());
            }
        }
        return stack.pop();
    }

    private Boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    private Boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
