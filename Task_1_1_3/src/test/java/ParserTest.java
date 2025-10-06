import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.Constant;
import org.example.Expression;
import org.example.ExpressionParser;
import org.example.Variable;
import org.junit.jupiter.api.Test;

class ParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    void testParseNumber() {
        Expression e = parser.parse("123.45");
        assertTrue(e instanceof Constant);
        assertEquals("123.45", e.toString());
    }

    @Test
    void testParseVariable() {
        Expression e = parser.parse("myVar");
        assertTrue(e instanceof Variable);
        assertEquals("myVar", e.toString());
    }

    @Test
    void testParseComplexExpression() {
        String input = "(3.0+(2.0*x))";
        Expression e = parser.parse(input);
        assertEquals(input, e.toString());
    }
}