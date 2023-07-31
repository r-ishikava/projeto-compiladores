package compiler.expressions;

import compiler.structures.SymbolTable;

/**
 * Class for arithmetic expressions.
 */
public class ArithmeticExpression extends Expression {
    private String expression;
    private SymbolTable symbolTable;

    public ArithmeticExpression(String expression, SymbolTable symbolTable) {
        this.expression = expression;
        this.symbolTable = symbolTable;
    }

    @Override
    public String toString() {
        return this.expression;
    }

    @Override
    public String calculate() {
        PostfixExpression postfixExpression = new PostfixExpression(this.expression, this.symbolTable);
        return postfixExpression.calculate();
    }
}
