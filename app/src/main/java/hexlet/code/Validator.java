package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

@SuppressWarnings("java:S1118")
public class Validator {
    public static StringSchema string() {
        return new StringSchema();
    }

    public static NumberSchema number() {
        return new NumberSchema();
    }

    public static MapSchema map() {
        return new MapSchema();
    }
}
