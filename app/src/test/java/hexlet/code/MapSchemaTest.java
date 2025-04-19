package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
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

    @Test
    void testShapeValidCase() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> rules = new HashMap<>();
        rules.put("firstName", v.string().required());
        rules.put("lastName", v.string().required().minLength(2));

        schema.shape(rules);

        Map<String, String> person = new HashMap<>();
        person.put("firstName", "Alice");
        person.put("lastName", "Smith");

        assertTrue(schema.isValid(person));
    }

    @Test
    void testShapeNullField() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> rules = new HashMap<>();
        rules.put("firstName", v.string().required());
        rules.put("lastName", v.string().required().minLength(2));

        schema.shape(rules);

        Map<String, String> person = new HashMap<>();
        person.put("firstName", "John");
        person.put("lastName", null); // <- невалидно

        assertFalse(schema.isValid(person));
    }

    @Test
    void testShapeTooShort() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> rules = new HashMap<>();
        rules.put("firstName", v.string().required());
        rules.put("lastName", v.string().required().minLength(2));

        schema.shape(rules);

        Map<String, String> person = new HashMap<>();
        person.put("firstName", "Anna");
        person.put("lastName", "B"); // <- меньше двух символов

        assertFalse(schema.isValid(person));
    }

}
