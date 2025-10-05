import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.Expression;
import org.example.Variable;
import org.junit.jupiter.api.Test;

public class ExpressionTest {
    @Test
    void testEval_ThrowsExceptionForUndefinedVariable() {
        Expression e = new Variable("y");
        assertThrows(IllegalArgumentException.class, () -> {e.eval("x=10.0");});
    }
}
