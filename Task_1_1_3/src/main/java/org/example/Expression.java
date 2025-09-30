package org.example;
import java.util.Map;

public abstract class Expression {

    /**
     * Returns the string representation of the expression.
     * @return a string, for example, "(1+x)".
     */
    @Override
    public abstract String toString();

    /**
     * Evaluates the value of the expression by substituting variable values.
     * @param variables a map where the key is the variable name and the value is its numeric value.
     * @return the result of the evaluation.
     */
    public abstract double eval(Map<String, Double> variables);

    /**
     * Finds the symbolic derivative of the expression with respect to a given variable.
     * @param varName the name of the variable to differentiate with respect to.
     * @return a new expression representing the derivative.
     */
    public abstract Expression derivative(String varName);
}