package org.example;
import java.util.Map;

public class Add extends BinaryOperation {

    /**
     * Constructs an addition expression.
     * @param left the left-hand side expression.
     * @param right the right-hand side expression.
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    @Override
    public double eval(Map<String, Double> variables) {
        return left.eval(variables) + right.eval(variables);
    }

    @Override
    public Expression derivative(String varName) {
        return new Add(left.derivative(varName), right.derivative(varName));
    }
}
