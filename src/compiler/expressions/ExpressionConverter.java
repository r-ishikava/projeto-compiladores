package compiler.expressions;

import java.util.List;
import java.util.Stack;

import compiler.structures.SymbolTable;

/**
 * Class to convert expressions.
 */
public abstract class ExpressionConverter {
    /**
     * Supported operators and their precedence values.
     */
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

    /**
     * Converts an infix expression to a postfix expression.
     *
     * Uses the stack algorithm for the conversion. Puts a whitespace between the elements.
     *
     * @param expression String representation of an infix expression. Ex: 12 + 5 - 3 * 9 / 1 + ( 1 + 2 ).
     * @return PostfixExpression object with the postfix representation of given expression. Ex: 12 5 + 3 9 * 1 / - 1 2 + +.
     */
    public static PostfixExpression infixToPostfix(String expression, SymbolTable symbolTable) {
        Stack<Operator> operator_stack = new Stack<>(); 
        StringBuilder convertedExpression = new StringBuilder();
        Boolean number_body = true;
        Character last_symbol = null;
        List<Character> operators = List.of('+', '-', '*', '/');
        for (Character c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == ',' || Character.isLetter(c)) {
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
                        //Handles negations by adding a null element to the expression
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
                        throw new RuntimeException("Invalid operator");
                }
                // Pops from the stack all operators with a precedence equal or higher than the current operator and put in the expression
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
                // Pops all the operators until it finds '('
                while (operator_stack.peek().getSymbol() != '(') {
                    convertedExpression.append(' ');
                    convertedExpression.append(operator_stack.pop().getSymbol());
                }
                operator_stack.pop();
            }
            last_symbol = c;
        }
        // Put the remaining operators in the expression
        while (!operator_stack.empty()) {
            convertedExpression.append(' ');
            convertedExpression.append(operator_stack.pop().getSymbol());
        }
        return new PostfixExpression(convertedExpression.toString(), symbolTable);
    } 
}
