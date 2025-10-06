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
}
