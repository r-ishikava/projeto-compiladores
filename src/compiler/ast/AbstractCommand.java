package compiler.ast;

/**
 * Abstract class for Command type classes.
 *
 * Generates the target code.
 */
public abstract class AbstractCommand {
    /**
     * Sets the indentation level throughout the program. n times \t.
     */
    protected static int indentationLevel;
    public abstract String generateCCode();   
    public abstract String generateJavaCode();
}
