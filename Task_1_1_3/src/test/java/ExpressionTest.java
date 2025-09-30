import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Expression;
import org.example.Add;
import org.example.Constant;
import org.example.Variable;
import org.example.Mul;
import org.example.ExpressionParser;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class ExpressionTest {

    @Test
    void testExpressionToString() {
        // (3.0 + (2.0 * x))
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        assertEquals("(3.0+(2.0*x))", e.toString());
    }

    @Test
    void testExpressionEval() {
        // (3.0 + (2.0 * x)) with x = 10.0
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        // eval with x = 10.0 should be 3.0 + (2.0 * 10.0) = 23.0
        double result = e.eval(Map.of("x", 10.0));
        assertEquals(23.0, result);
    }

    @Test
    void testExpressionDerivative() {
        // Derivative of (3.0 + x) with respect to "x"
        // d/dx (3.0 + x) = d/dx(3.0) + d/dx(x) = 0 + 1 = 1
        Expression e = new Add(new Constant(3), new Variable("x"));
        Expression derivative = e.derivative("x");

        // The resulting expression is (0.0 + 1.0)
        assertEquals("(0.0+1.0)", derivative.toString());

        // The value of the derivative is always 1
        assertEquals(1.0, derivative.eval(Map.of("x", 10.0)));
    }

    @Test
    void testComplexDerivative() {
        // Derivative of (2.0 * x) with respect to "x"
        // d/dx (2.0 * x) = (d/dx(2.0) * x) + (2.0 * d/dx(x)) = (0 * x) + (2 * 1) = 2
        Expression e = new Mul(new Constant(2), new Variable("x"));
        Expression derivative = e.derivative("x");

        // The resulting expression is ((0.0*x)+(2.0*1.0))
        assertEquals("((0.0*x)+(2.0*1.0))", derivative.toString());

        // The value of the derivative is always 2
        assertEquals(2.0, derivative.eval(Map.of("x", 10.0)));
    }

    @Test
    void testParser() {
        ExpressionParser parser = new ExpressionParser();
        String input = "(3.0+(2.0*x))";

        // 1. Парсим строку
        Expression e = parser.parse(input);

        // 2. Проверяем, что toString() воссоздает исходную строку
        assertEquals(input, e.toString());

        // 3. Проверяем, что eval() работает правильно для созданного объекта
        double result = e.eval(Map.of("x", 10.0));
        assertEquals(23.0, result);
    }
}