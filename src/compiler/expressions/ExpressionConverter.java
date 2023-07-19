package compiler.expressions;

import java.util.List;
import java.util.Stack;

public abstract class ExpressionConverter {
    private enum Operator {
        SUM('+', 1),
        SUB('-', 1),
        MUL('*', 2),
        DIV('/', 2),
        PAR('(', 0);

        private Character symbol;
        private int value;

        private Operator(Character symbol, int value) {
            this.symbol = symbol;   
            this.value = value;
        }

        public Character getSymbol() {
            return this.symbol;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static PostfixExpression infixToPostfix(String expression) {
        Stack<Operator> operator_stack = new Stack<>(); 
        StringBuilder convertedExpression = new StringBuilder();
        Boolean number_body = true;
        Character last_symbol = null;
        List<Character> operators = List.of('+', '-', '*', '/');
        for (Character c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == ',') {
                convertedExpression.append(c);
                number_body = true;
            } else if (operators.contains(c)) {
                if (number_body && !convertedExpression.isEmpty()) {
                    convertedExpression.append(' ');
                }
                number_body = false;
                Operator operator;
                switch (c) {
                    case '+':
                        operator = Operator.SUM;
                        break;
                    case '-':
                        if (last_symbol == null || operators.contains(last_symbol) || last_symbol == '(') {
                            convertedExpression.append('0');
                            convertedExpression.append(' ');
                        }
                        operator = Operator.SUB;
                        break;
                    case '*':
                        operator = Operator.MUL;
                        break;
                    case '/':
                        operator = Operator.DIV;
                        break;
                    default:
                        throw new RuntimeException("I don't think this should ever have happened");
                }
                if (!operator_stack.empty() && operator.getValue() <= operator_stack.peek().getValue()) {
                    while (!operator_stack.empty() && operator_stack.peek().getValue() >= operator.getValue()) {
                        convertedExpression.append(operator_stack.pop().getSymbol());
                        convertedExpression.append(' ');
                    }
                }
                operator_stack.push(operator);
            } else if (c == '(') {
                operator_stack.push(Operator.PAR);
            } else if (c == ')') {
                while (operator_stack.peek().getSymbol() != '(') {
                    convertedExpression.append(' ');
                    convertedExpression.append(operator_stack.pop().getSymbol());
                }
                operator_stack.pop();
            }
            last_symbol = c;
        }
        while (!operator_stack.empty()) {
            convertedExpression.append(' ');
            convertedExpression.append(operator_stack.pop().getSymbol());
        }
        return new PostfixExpression(convertedExpression.toString());
    } 
}