package org.example;
import java.util.Map;
import java.util.Objects;

public class Variable extends Expression {

    private final String name;

    /**
     * Constructs a variable expression.
     * @param name the name of the variable (e.g., "x").
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        // Просто возвращаем имя переменной
        return this.name;
    }

    @Override
    public double eval(Map<String, Double> variables) {
        // Ищем значение этой переменной в переданной карте
        return variables.get(this.name);
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
