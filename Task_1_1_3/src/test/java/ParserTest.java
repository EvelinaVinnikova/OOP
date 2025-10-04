import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Expression;
import org.example.ExpressionParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ParserTest {
    @Test
    void testParser() {
        ExpressionParser parser = new ExpressionParser();
        String input = "(3.0+(2.0*x))";

        Expression e = parser.parse(input);

        assertEquals(input, e.toString());

        double result = e.eval("x=10.0");
        assertEquals(23.0, result);
    }
}
