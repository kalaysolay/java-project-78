package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ValidatorTest {
    @Test
    void mapSchemaTest() {
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
        assertTrue(schema.isValid(6));
    }


    @Test
    void testInitialValidState() {
        var v = new Validator();
        var schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("somevalue"));
    }

    @Test
    void testRequired() {
        var schema = new Validator().string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("text"));
    }

    @Test
    void testMinLength() {
        var schema = new Validator().string().required().minLength(4);
        assertFalse(schema.isValid("cat"));
        assertTrue(schema.isValid("lion"));
        assertTrue(schema.isValid("tiger"));
    }

    @Test
    void testContains() {
        var schema = new Validator().string().required().contains("hex");
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid("hello"));
    }

    @Test
    void testChainedChecks() {
        var schema = new Validator().string().required().minLength(5).contains("fox");

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("fox"));
        assertFalse(schema.isValid("something else"));
    }

    @Test
    void testIsValidNull() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(null));

    }

    @Test
    void testIsValidEmpty() {
        String testString = "";
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(testString));
    }

    @Test
    void testIsValidRequiredNull() {
        var v = new Validator();
        var schema = v.string().required();
        assertFalse(schema.isValid(null));

    }

    @Test
     void testIsValidRequiredEmpty() {
        String testString = "";
        var v = new Validator();
        var schema = v.string().required();
        assertFalse(schema.isValid(testString));
    }

    @Test
    void testIsValid() {
        String testString = "grin without a cat";
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(testString));

    }

    @Test
    void testIsValidContainsEmpty() {
        String testString = "grin without a cat";
        var v = new Validator();
        var schema = v.string().contains("");
        assertTrue(schema.isValid(testString));
    }

    @Test
    void testIsValidContains() {
        String testString = "grin without a cat";
        var v = new Validator();
        var schema = v.string().contains("grin");
        assertTrue(schema.isValid(testString));
    }

    @Test
    void testIsValidContainsMinLengthTrue() {
        String testString = "grin without a cat";
        var v = new Validator();
        var schema = v.string().minLength(5);
        assertTrue(schema.isValid(testString));
    }

    @Test
    void testIsValidContainsMinlengthFalse() {
        String testString = "grin without a cat";
        var v = new Validator();
        var schema = v.string().minLength(200);
        assertFalse(schema.isValid(testString));
    }
}
