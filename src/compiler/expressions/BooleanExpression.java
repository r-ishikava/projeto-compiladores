package compiler.expressions;

public class BooleanExpression extends Expression {
    private String expression;

    public BooleanExpression(String expression) {
        super();
        this.expression = expression;
    }

    public BooleanExpression() {
        super();
        this.expression = "";
    }

    @Override
    public String toString() {
        return this.expression;
    }

    public void append(String append) {
        this.expression = this.expression + append;
    }

    // May be possible to implement it with a stack of RelationalExpressions.
    @Override
    public String calculate() {
        throw new UnsupportedOperationException("Unimplemented method 'calculate'");
    }
}
