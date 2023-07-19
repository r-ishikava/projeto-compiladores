package compiler.expressions;

import java.util.Stack;

public class PostfixExpression extends Expression {
    private Stack<String> stack;
    private String expression;

    protected PostfixExpression(String expression) {
        super();
        this.expression = expression;
        this.stack = new Stack<>();
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public String calculate() {
        Boolean floatFlag = false;
        String[] expressionElements = expression.split(" ");
        for (String element : expressionElements) {
            element = element.replace(',', '.');
            if (isInteger(element) || isFloat(element)) {
                stack.push(element);
            } else {
                Number res;
                String a = stack.pop();
                String b = stack.pop();
                if (a.contains(String.valueOf('.')) || b.contains(String.valueOf('.'))) {
                    floatFlag = true;
                }
                if (element.equals("+")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) + Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) + Integer.parseInt(a);
                    }
                } else if (element.equals("-")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) - Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) - Integer.parseInt(a);
                    }
                } else if (element.equals("*")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) * Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) * Integer.parseInt(a);
                    }
                } else if (element.equals("/")) {
                    if (floatFlag) {
                        res = Float.parseFloat(b) / Float.parseFloat(a);
                    } else {
                        res = Integer.parseInt(b) / Integer.parseInt(a);
                    }
                } else {
                    throw new RuntimeException("Unrecognized operator '" + element + "' found in the expression");
                }
                stack.push(res.toString());
            }
        }
        return stack.pop();
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
}
