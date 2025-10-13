package org.example;

import java.util.HashMap;
import java.util.Map;


/**
 * Base class for all mathematical expressions.
 */
public abstract class Expression {

    /**
     * Returns the string representation of the expression.
     *
     * @return a string, for example, "(1+x)".
     */
    @Override
    public abstract String toString();

    /**
     * Finds the symbolic derivative of the expression with respect to a given variable.
     *
     * @param varName the name of the variable to differentiate with respect to.
     * @return a new expression representing the derivative.
     */
    public abstract Expression derivative(String varName);

    /**
     * Evaluates the value of the expression...
     *
     * @param variables a map...
     * @return the result of the evaluation.
     * @throws IllegalArgumentException if a variable is undefined.
     */
    protected abstract double eval(Map<String, Double> variables);


    /**
     * Evaluates the expression by parsing a string...
     *
     * @param variablesString a string like "x = 10; y = 13".
     * @return the result of the evaluation.
     * @throws IllegalArgumentException if a variable is undefined.
     */
    public double eval(String variablesString) {
        Map<String, Double> variablesMap = new HashMap<>();

        String[] assignments = variablesString.split(";");

        for (String assignment : assignments) {
            String[] parts = assignment.split("=");
            if (parts.length == 2) {
                String varName = parts[0].trim();
                String valueStr = parts[1].trim();

                variablesMap.put(varName, Double.parseDouble(valueStr));
            }
        }

        return this.eval(variablesMap);
    }
}