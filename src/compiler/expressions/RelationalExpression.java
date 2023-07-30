package compiler.expressions;

public class RelationalExpression extends Expression {
    private ArithmeticExpression leftSide;
    private ArithmeticExpression rightSide;
    private String operator;

    public RelationalExpression(ArithmeticExpression leftSide, ArithmeticExpression rightSide, String operator) {
        super();
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    public RelationalExpression() {
        super();
    }

    @Override
    public String toString() {
        return this.leftSide.toString() + this.operator + this.rightSide.toString();
    }

    @Override
    public String calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
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
}
