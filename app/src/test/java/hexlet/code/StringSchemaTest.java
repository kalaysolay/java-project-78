package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

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
}
