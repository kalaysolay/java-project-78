package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    void testNumberSchema() {
        var v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null)); // null разрешен

        schema.positive();
        assertTrue(schema.isValid(null)); // все еще true
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0)); // 0 — не положительное

        schema.required(); // теперь null не допустим
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(15));
        assertFalse(schema.isValid(-5));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
