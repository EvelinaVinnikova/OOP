package org.example;

import java.util.Map;
import java.util.Objects;


/**
 * Represents a variable in an expression.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * Constructs a variable expression.
     *
     * @param name the variable name (e.g., {@code "x"}).
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Evaluates the expression by parsing a string...
     *
     * @return the result of the evaluation.
     * @throws IllegalArgumentException if a variable in the expression is not defined in the string.
     */
    public double eval(Map<String, Double> variables) {
        Double value = variables.get(this.name);
        if (value == null) {
            throw new IllegalArgumentException("Variable '" + this.name + "' is not defined.");
        }
        return value;
    }

    @Override
    public Expression derivative(String varName) {
        // Если мы дифференцируем по этой же переменной (dx/dx), результат - 1.
        // В противном случае (dy/dx), результат - 0.
        if (Objects.equals(this.name, varName)) {
            return new Constant(1);
        } else {
            return new Constant(0);
        }
    }
}
