import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Expression;
import org.example.Add;
import org.example.Constant;
import org.example.Variable;
import org.example.Mul;

import org.junit.jupiter.api.Test;

public class ExpressionToStringTest {

    @Test
    void testExpressionToString() {
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        assertEquals("(3.0+(2.0*x))", e.toString());
    }
}