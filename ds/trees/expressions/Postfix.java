package ds.trees.expressions;

import java.util.Stack;

/**
 * What is a Postfix Expression?
 * 
 * When a mathematical expression is written in postfix form, operators follow
 * their operands; for instance, to add 3 and 4, one would write "34+" rather
 * than "3+4".
 * 
 * If there are multiple operations, the operator is given immediately after its
 * last operand; so the expression written "3−4+5" in conventional notation
 * (which is also called infix notation) would be written "34−5+" in postfix
 * form: 4 is first subtracted from 3, then 5 added to it. Note, this means the
 * operands to the first operator (reading left to right) are to its immediate
 * left.
 * 
 * An advantage of postfix form is that it eliminates the need for parentheses
 * that are required by infix notation (where operators come between their
 * operands). While "3−4×5" can also be written "3−(4×5)", that means something
 * quite different from "(3−4)×5".
 * 
 * In postfix, the first expression ("3−4×5") can be written "345×−", which
 * unambiguously means "3(45×)−" which reduces to "320−" and then ultimately to
 * "−17".
 * 
 * The expression "(3−4)×5", on the other hand could be written "34−5×", or
 * alternatively as "534−×", if there was a desire to keep the operators at the
 * end. In both cases, one ends up with the product of −1 and 5.
 * 
 * Postfix notation is also called Reverse Polish Notation (RPN).
 */

public class Postfix {

    /**
     * Evaluate an input Postfix Expression. Uses a stack.
     * 
     * @param expression - String
     * @return output - int
     */
    public static int evaluate(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } else {

                if (stack.size() < 2) {
                    System.out.println("Invalid Postfix Expression");
                    return Integer.MIN_VALUE;
                }

                int first = stack.pop();
                int second = stack.pop();

                if (c == '-') {
                    stack.push(second - first);
                } else if (c == '+') {
                    stack.push(second + first);
                } else if (c == '/') {
                    stack.push(second / first);
                } else if (c == 'x') {
                    stack.push(second * first);
                } else if (c == '^') {
                    stack.push((int) Math.pow(second, first));
                } else {
                    System.out.println("Invalid Postfix Expression");
                    return Integer.MIN_VALUE;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("512+4x+3-"));
        System.out.println(evaluate("723x-4^93/+"));
    }
}
