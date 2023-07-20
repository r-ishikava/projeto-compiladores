package compiler.ast;

public abstract class AbstractCommand {
    public abstract String generateCCode();   
    public abstract String generateJavaCode();
}
