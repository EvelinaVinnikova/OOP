import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Expression;
import org.example.Add;
import org.example.Constant;
import org.example.Variable;
import org.example.Mul;
import org.example.ExpressionParser;
import org.example.Sub;
import org.example.Div;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class ExpressionTest {

    @Test
    void testExpressionToString() {
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        assertEquals("(3.0+(2.0*x))", e.toString());
    }

    @Test
    void testExpressionEval() {
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        double result = e.eval(Map.of("x", 10.0));
        assertEquals(23.0, result);
    }

    @Test
    void testExpressionDerivative() {
        Expression e = new Add(new Constant(3), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("(0.0+1.0)", derivative.toString());

        assertEquals(1.0, derivative.eval(Map.of("x", 10.0)));
    }

    @Test
    void testComplexDerivative() {
        Expression e = new Mul(new Constant(2), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("((0.0*x)+(2.0*1.0))", derivative.toString());

        assertEquals(2.0, derivative.eval(Map.of("x", 10.0)));
    }

    @Test
    void testParser() {
        ExpressionParser parser = new ExpressionParser();
        String input = "(3.0+(2.0*x))";

        Expression e = parser.parse(input);

        assertEquals(input, e.toString());

        double result = e.eval(Map.of("x", 10.0));
        assertEquals(23.0, result);
    }

    @Test
    void testSubtraction() {
        Expression e = new Sub(new Constant(10), new Variable("x"));

        assertEquals("(10.0-x)", e.toString());

        assertEquals(7.0, e.eval(Map.of("x", 3.0)));

        Expression derivative = e.derivative("x");
        assertEquals(-1.0, derivative.eval(Map.of()));
    }

    @Test
    void testDivision() {
        Expression e = new Div(new Variable("x"), new Constant(2));

        assertEquals("(x/2.0)", e.toString());

        assertEquals(5.0, e.eval(Map.of("x", 10.0)));

        Expression derivative = e.derivative("x");
        assertEquals(0.5, derivative.eval(Map.of()));
    }
}