package compiler.expressions;

/**
 * Abstract class for Expression classes.
 */
public abstract class Expression {
    /**
     * @return String representation of the expression.
     */
    public abstract String getExpression();
    /**
     * @return String representation of the expression evaluation.
     */
    public abstract String calculate();
}
