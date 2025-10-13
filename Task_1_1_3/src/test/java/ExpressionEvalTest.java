import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Expression;
import org.example.Add;
import org.example.Constant;
import org.example.Variable;
import org.example.Mul;
import org.junit.jupiter.api.Test;


class ExpressionEvalTest {

    @Test
    void testExpressionEval() {
        Expression e = new Add(
                new Constant(3),
                new Mul(new Constant(2), new Variable("x"))
        );
        assertEquals(23.0, e.eval("x = 10.0"));
    }
}