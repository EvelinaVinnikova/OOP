package org.example;
import java.util.Map;

public class Div extends BinaryOperation {

    /**
     * Constructs a division expression.
     * @param left the numerator expression.
     * @param right the denominator expression.
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        // Формируем строку вида "(left/right)"
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    @Override
    public double eval(Map<String, Double> variables) {
        // Рекурсивно вычисляем и делим результаты
        return left.eval(variables) / right.eval(variables);
    }

    @Override
    public Expression derivative(String varName) {
        // Применяем правило частного: (f/g)' = (f'*g - f*g') / g^2
        // f' = left.derivative(varName)
        // g' = right.derivative(varName)
        // f = left
        // g = right
        return new Div(
                new Sub(
                        new Mul(left.derivative(varName), right),
                        new Mul(left, right.derivative(varName))
                ),
                new Mul(right, right) // g^2
        );
    }
}
