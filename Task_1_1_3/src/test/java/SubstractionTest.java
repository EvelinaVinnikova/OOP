import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Constant;
import org.example.Expression;
import org.example.Sub;
import org.example.Variable;
import org.junit.jupiter.api.Test;


public class SubstractionTest {
    @Test
    void testSubtraction() {
        Expression e = new Sub(new Constant(10), new Variable("x"));

        assertEquals("(10.0-x)", e.toString());

        assertEquals(7.0, e.eval("x=3.0"));

        Expression derivative = e.derivative("x");
        assertEquals(-1.0, derivative.eval(""));
    }
}
