package compiler.expressions;

import compiler.structures.SymbolTable;

/**
 * Class for relational expressions.
 */
public class RelationalExpression extends Expression {
    private ArithmeticExpression leftSide;
    private ArithmeticExpression rightSide;
    private String operator;
    private SymbolTable symbolTable;

    public RelationalExpression(ArithmeticExpression leftSide, ArithmeticExpression rightSide, String operator, SymbolTable symbolTable) {
        super();
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
        this.symbolTable = symbolTable;
    }

    public RelationalExpression() {
        super();
    }

    @Override
    public String toString() {
        return this.leftSide.toString() + this.operator + this.rightSide.toString();
    }

    /**
     * Return true or false as a string, can not compare values of different types.
     */
    @Override
    public String calculate() {
        Boolean result;
        String left = this.leftSide.calculate();
        String right = this.rightSide.calculate();
        if (isInteger(left) && isInteger(right)) {
            switch (this.operator) {
                case ">":
                    result = Integer.parseInt(left) > Integer.parseInt(right);
                    break;
                case "<":
                    result = Integer.parseInt(left) < Integer.parseInt(right);
                    break;
                case ">=":
                    result = Integer.parseInt(left) >= Integer.parseInt(right);
                    break;
                case "<=":
                    result = Integer.parseInt(left) <= Integer.parseInt(right);
                    break;
                case "!=":
                    result = Integer.parseInt(left) != Integer.parseInt(right);
                    break;
                case "==":
                    result = Integer.parseInt(left) == Integer.parseInt(right);
                    break;
                default:
                    throw new RuntimeException("Invalid operator in relational expression");
            }
        } else if (isFloat(left) && isFloat(right)) {
            switch (this.operator) {
                case ">":
                    result = Float.parseFloat(left) > Float.parseFloat(right);
                    break;
                case "<":
                    result = Float.parseFloat(left) < Float.parseFloat(right);
                    break;
                case ">=":
                    result = Float.parseFloat(left) >= Float.parseFloat(right);
                    break;
                case "<=":
                    result = Float.parseFloat(left) <= Float.parseFloat(right);
                    break;
                case "!=":
                    result = Float.parseFloat(left) != Float.parseFloat(right);
                    break;
                case "==":
                    result = Float.parseFloat(left) == Float.parseFloat(right);
                    break;
                default:
                    throw new RuntimeException("Invalid operator in relational expression");
            }
        } else {
            throw new RuntimeException("Trying to compare two different types of expressions");
        }
        return result.toString();
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

    public ArithmeticExpression getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(ArithmeticExpression leftSide) {
        this.leftSide = leftSide;
    }

    public ArithmeticExpression getRightSide() {
        return rightSide;
    }

    public void setRightSide(ArithmeticExpression rightSide) {
        this.rightSide = rightSide;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
