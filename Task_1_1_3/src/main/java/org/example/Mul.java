package org.example;

import java.util.Map;


/**
 * Binary multiplication of two expressions.
 */
public class Mul extends BinaryOperation {

    /**
     * Constructs a multiplication expression.
     *
     * @param left the left-hand side expression.
     * @param right the right-hand side expression.
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public double eval(Map<String, Double> variables) {
        return left.eval(variables) * right.eval(variables);
    }

    @Override
    public Expression derivative(String varName) {
        // (f*g)' = f'*g + f*g'
        // f' = left.derivative(varName)
        // g' = right.derivative(varName)
        // f = left
        // g = right
        return new Add(
                new Mul(left.derivative(varName), right),
                new Mul(left, right.derivative(varName))
        );
    }
}