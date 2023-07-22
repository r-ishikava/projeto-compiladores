package compiler.ast;

public abstract class AbstractCommand {
    protected static int indentationLevel;
    public abstract String generateCCode();   
    public abstract String generateJavaCode();
}
