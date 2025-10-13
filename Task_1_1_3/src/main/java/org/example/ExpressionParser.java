package org.example;


/**
 * A simple parser for expressions with +, -, *, / and variables.
 */
public class ExpressionParser {

    /**
     * Parses a string into an {@link Expression}.
     *
     * @param str the string to parse
     * @return parsed expression
     * @throws IllegalArgumentException if the string cannot be parsed
     */
    public Expression parse(String str) {
        str = str.trim();

        try {
            double value = Double.parseDouble(str);
            return new Constant(value);
        } catch (NumberFormatException e) {
            // ignored: not a numeric constant
        }

        if (str.matches("[a-zA-Z]+")) {
            return new Variable(str);
        }

        if (str.startsWith("(") && str.endsWith(")")) {
            String sub = str.substring(1, str.length() - 1);

            int balance = 0;
            int mainOpIndex = -1;
            int highOpIndex = -1;

            for (int i = sub.length() - 1; i >= 0; i--) {
                char c = sub.charAt(i);
                if (c == ')') {
                    balance++;
                } else if (c == '(') {
                    balance--;
                }

                if (balance == 0) {
                    if (c == '+' || c == '-') {
                        mainOpIndex = i;
                        break;
                    }
                    if ((c == '*' || c == '/') && highOpIndex == -1) {
                        highOpIndex = i;
                    }
                }
            }

            int splitIndex = (mainOpIndex != -1) ? mainOpIndex : highOpIndex;

            if (splitIndex != -1) {
                Expression left = parse(sub.substring(0, splitIndex));
                Expression right = parse(sub.substring(splitIndex + 1));

                switch (sub.charAt(splitIndex)) {
                    case '+': return new Add(left, right);
                    case '-': return new Sub(left, right);
                    case '*': return new Mul(left, right);
                    case '/': return new Div(left, right);
                    default:
                        throw new IllegalArgumentException(
                                "Unknown operator: " + sub.charAt(splitIndex));
                }
            }
        }

        throw new IllegalArgumentException("Cannot parse expression: " + str);
    }
}