import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Add;
import org.example.Mul;
import org.example.Expression;
import org.example.Constant;
import org.example.Variable;
import org.junit.jupiter.api.Test;


public class ExpressionDerivativeTest {
    @Test
    void testExpressionDerivative() {
        Expression e = new Add(new Constant(3), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("(0.0+1.0)", derivative.toString());
        assertEquals(1.0, derivative.eval(""));

        Expression b = new Mul(new Constant(2), new Variable("x"));
        Expression b_derivative = b.derivative("x");

        assertEquals("((0.0*x)+(2.0*1.0))", b_derivative.toString());
        assertEquals(2.0, b_derivative.eval("x=10.0"));
    }
}