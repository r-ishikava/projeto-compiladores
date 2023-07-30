package compiler.expressions;

public class ArithmeticExpression extends Expression {
    private String expression;

    public ArithmeticExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.expression;
    }

    @Override
    public String calculate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
