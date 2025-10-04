import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Constant;
import org.example.Expression;
import org.example.Mul;
import org.example.Variable;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ComplexDerivativeTest {
    @Test
    void testComplexDerivative() {
        Expression e = new Mul(new Constant(2), new Variable("x"));
        Expression derivative = e.derivative("x");

        assertEquals("((0.0*x)+(2.0*1.0))", derivative.toString());

        assertEquals(2.0, derivative.eval("x=10.0"));
    }
}
