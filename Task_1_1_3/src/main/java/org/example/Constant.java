package org.example;

import java.util.Map;


/**
 * Numeric literal expression.
 */
public class Constant extends Expression {

    private final double value;

    /**
     * Constructs a number expression.
     *
     * @param value the constant value.
     */
    public Constant(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public double eval(Map<String, Double> variables) {
        return this.value;
    }

    @Override
    public Expression derivative(String varName) {
        return new Constant(0);
    }
}