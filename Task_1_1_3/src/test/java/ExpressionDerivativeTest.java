import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Add;
import org.example.Constant;
import org.example.Expression;
import org.example.Variable;
import org.junit.jupiter.api.Test;

import java.util.Map;


public class ExpressionDerivativeTest {
    @Test
    void testExpressionDerivative() {
        Expression e = new Add(new Constant(3), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("(0.0+1.0)", derivative.toString());

        assertEquals(1.0, derivative.eval("x=10.0"));
    }
}