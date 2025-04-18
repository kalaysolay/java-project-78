package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    @Test
    void mapSchemaTest() {
        System.out.println("Starting TEST MAP");
        var v = new Validator();
        var schema = v.map();

        // Начальное поведение без required()
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        assertTrue(schema.isValid(map));

        schema.sizeof(2);

        assertFalse(schema.isValid(map));
        map.put("key2", "value2");
        assertTrue(schema.isValid(map));
        map.put("key3", "value3");
        assertFalse(schema.isValid(map));
    }
}
