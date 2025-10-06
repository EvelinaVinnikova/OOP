package org.example;

import java.util.Map;


/**
 * Binary subtraction of two expressions.
 */
public class Sub extends BinaryOperation {

    /**
     * Constructs a subtraction expression.
     *
     * @param left the left-hand side expression.
     * @param right the right-hand side expression.
     */
    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }

    @Override
    public double eval(Map<String, Double> variables) {
        return left.eval(variables) - right.eval(variables);
    }

    @Override
    public Expression derivative(String varName) {
        // (f-g)' = f' - g'
        return new Sub(left.derivative(varName), right.derivative(varName));
    }
}