import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Add;
import org.example.Mul;
import org.example.Expression;
import org.example.Constant;
import org.example.Variable;
import org.junit.jupiter.api.Test;


/**
 * Tests for the symbolic derivative string form.
 */
public class ExpressionDerivativeTest {

    /**
     * Checks that d/dx (3 + x) == 1.
     */
    @Test
    void derivativeOfAddСonstPlusVar() {
        Expression expr = new Add(new Constant(3), new Variable("x"));
        Expression exprDerivative = expr.derivative("x");
        assertEquals("(0.0+1.0)", exprDerivative.toString());
    }

    /**
     * Checks that d/dx (2 * x) == 2 using the product rule with a constant.
     */
    @Test
    void derivativeOfMulСonstTimesVar() {
        Expression mulExpr = new Mul(new Constant(2), new Variable("x"));
        Expression mulDerivative = mulExpr.derivative("x");
        assertEquals("((0.0*x)+(2.0*1.0))", mulDerivative.toString());
    }
}