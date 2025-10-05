import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Constant;
import org.example.Div;
import org.example.Expression;
import org.example.Variable;
import org.junit.jupiter.api.Test;


public class DivisionTest {
    @Test
    void testDivision() {
        Expression e = new Div(new Variable("x"), new Constant(2));

        assertEquals("(x/2.0)", e.toString());

        assertEquals(5.0, e.eval("x=10.0"));

        Expression derivative = e.derivative("x");
        assertEquals(0.5, derivative.eval("x=10.0"));
    }
}
