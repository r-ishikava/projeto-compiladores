package compiler.expressions;

/**
 * Abstract class for Expression classes.
 */
public abstract class Expression {
    /**
     * @return String representation of the expression.
     */
    public abstract String toString();
    /**
     * @return String representation of the expression evaluation.
     */
    public abstract String calculate();
}
