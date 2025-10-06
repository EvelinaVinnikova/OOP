import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Constant;
import org.example.Expression;
import org.example.Sub;
import org.example.Variable;
import org.junit.jupiter.api.Test;


/**
 * Tests for subtraction expressions.
 */
public class SubstractionTest {

    /**
     * Checks string form of 10 - x.
     */
    @Test
    void testSubtraction() {
        Expression e = new Sub(new Constant(10), new Variable("x"));
        assertEquals("(10.0-x)", e.toString());
    }

    /**
     * Evaluates 10 - x at x = 3.0 -> 7.0
     */
    @Test
    void subtractionEvalAtX3() {
        Expression e = new Sub(new Constant(10), new Variable("x"));
        assertEquals(7.0, e.eval("x=3.0"));
    }

    /**
     * Checks derivative: d/dx (10 - x) == (0.0-1.0)
     */
    @Test
    void subtractionDerivativeToString() {
        Expression e = new Sub(new Constant(10), new Variable("x"));
        Expression d = e.derivative("x");
        assertEquals("(0.0-1.0)", d.toString());
    }
}
