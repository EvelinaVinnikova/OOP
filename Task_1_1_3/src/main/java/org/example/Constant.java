package org.example;
import java.util.Map;

public class Constant extends Expression {

    private final double value;

    /**
     * Constructs a number expression.
     * @param value the constant value.
     */
    public Constant(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        // Просто возвращаем строковое представление числа
        return String.valueOf(value);
    }

    @Override
    public double eval(Map<String, Double> variables) {
        // Значение константы не зависит от переменных, просто возвращаем его
        return this.value;
    }

    @Override
    public Expression derivative(String varName) {
        // Производная от любого числа (константы) всегда равна 0
        return new Constant(0);
    }
}