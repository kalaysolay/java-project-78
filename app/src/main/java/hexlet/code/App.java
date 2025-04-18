package hexlet.code;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        /*
        Этот класс нужен для проверок и запуска валидатора
         */
        var v = new Validator();
        var schema = v.string();

        System.out.println("value  \"\"");
        System.out.println(schema.isValid(""));      // true
        System.out.println("---------------------------");
        System.out.println("value = NULL");
        System.out.println(schema.isValid(null));    // true

        schema.required();
        System.out.println("value  \"\"");
        System.out.println(schema.isValid(""));      // false
        System.out.println("---------------------------");
        System.out.println("value = NULL");
        System.out.println(schema.isValid(null));    // false
        System.out.println("---------------------------");
        System.out.println("value = hexlet");
        System.out.println(schema.isValid("hexlet")); // true

        schema.minLength(5);
        System.out.println(schema.isValid("hex"));   // false
        System.out.println("---------------------------");
        System.out.println(schema.isValid("hexlet")); // true

        schema.contains("hex");
        System.out.println("---------------------------");
        System.out.println("Schema. Contains = hex. Testing value = hexlet");
        System.out.println(schema.isValid("hexlet")); // true
        System.out.println("---------------------------");
        System.out.println("Schema. Contains = hex. Testing value = hetlet");
        System.out.println(schema.isValid("hetlet")); // false

        System.out.println("---------------------------");

        System.out.println("----------NUMBERS-------------");
        var v2 = new Validator();

        var schemaNumber = v2.number();

        schemaNumber.isValid(5); // true

// Пока не вызван метод required(), null считается валидным
        schemaNumber.isValid(null); // true
        schemaNumber.positive().isValid(null); // true

        schemaNumber.required();

        schemaNumber.isValid(null); // false
        schemaNumber.isValid(10); // true

// Потому что ранее мы вызвали метод positive()
        schemaNumber.isValid(-10); // false
//  Ноль — не положительное число
        schemaNumber.isValid(0); // false

        schemaNumber.range(5, 10);

        schemaNumber.isValid(5); // true
        schemaNumber.isValid(10); // true
        schemaNumber.isValid(4); // false
        schemaNumber.isValid(11); // false

        System.out.println("---------------------------");

        System.out.println("----------MAP-------------");

        var v3 = new Validator();

        var schemaMap = v3.map();

        System.out.println(schemaMap.isValid(null)); // true

        schemaMap.required();

        System.out.println(schemaMap.isValid(null)); // false
        System.out.println(schemaMap.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        System.out.println(schemaMap.isValid(data)); // true

        schemaMap.sizeof(2);

        System.out.println(schemaMap.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(schemaMap.isValid(data)); // true
    }
}
