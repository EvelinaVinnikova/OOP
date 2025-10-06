import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Constant;
import org.example.Div;
import org.example.Expression;
import org.example.Variable;
import org.junit.jupiter.api.Test;


/**
 * Tests for division expression behavior.
 */
public class DivisionTest {

    /**
     * Checks string form of x/2.
     */
    @Test
    void divisionToString() {
        Expression e = new Div(new Variable("x"), new Constant(2));
        assertEquals("(x/2.0)", e.toString());
    }

    /**
     * Evaluates x/2 at x = 10.0.
     */
    @Test
    void divisionEvalAtX10() {
        Expression e = new Div(new Variable("x"), new Constant(2));
        assertEquals(5.0, e.eval("x=10.0"));
    }

    /**
     * Evaluates derivative d/dx (x/2) at x = 10.0.
     */
    @Test
    void divisionDerivativeEvalAtX10() {
        Expression e = new Div(new Variable("x"), new Constant(2));
        Expression d = e.derivative("x");
        assertEquals(0.5, d.eval("x=10.0"));
    }
}