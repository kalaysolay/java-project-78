package hexlet.code;

import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("-----------");
        var hexv = new Validator();

        var schemaHex = hexv.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        System.out.println("Required = false");
        System.out.println("schemaHex.isValid(\"\")");
        System.out.println(schemaHex.isValid("")); // true

        System.out.println("schemaHex.isValid(null)");
        System.out.println(schemaHex.isValid(null)); // true

        System.out.println("Установили required = true");
        schemaHex.required();

        System.out.println("schemaHex.isValid(null) : ");
        System.out.println(schemaHex.isValid(null)); // false

        System.out.println("schemaHex.isValid(\"\")");
        System.out.println(schemaHex.isValid("")); // false

        System.out.println("schemaHex.isValid(\"what does the fox say\")");
        System.out.println(schemaHex.isValid("what does the fox say")); // true

        System.out.println("schemaHex.isValid(\"hexlet\")");
        System.out.println(schemaHex.isValid("hexlet")); // true


        System.out.println(schemaHex.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schemaHex.contains("what").isValid("what does the fox say")); // true
        System.out.println(schemaHex.contains("whatthe").isValid("what does the fox say")); // false
        System.out.println(schemaHex.isValid("what does the fox say")); // false;

        // Если один валидатор вызывался несколько раз
        // то последний имеет приоритет (перетирает предыдущий)
        var schema1 = hexv.string();
        System.out.println("schema1.minLength(10).minLength(4).isValid(\"Hexlet\")");
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true

        System.out.println("################################");

        System.out.println("------------NUMBERS-------------");
        System.out.println("################################");
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

        System.out.println("################################");

        System.out.println("---------------MAP-------------");
        System.out.println("################################");

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

        System.out.println("################################");
        System.out.println("------------ SHAPE -------------");
        System.out.println("################################");
        var v4 = new Validator();

        var schemaShape = v4.map();

        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemasMap = new HashMap<>();

        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemasMap.put("firstName", v4.string().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemasMap.put("lastName", v4.string().required().minLength(2));

        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schemaShape.shape(schemasMap);
        System.out.println("Schema Map with firstname = required. Lastname = required, minlength = 2");
        System.out.println(schemaMap);
        // Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        System.out.println(human1);
        System.out.println(schemaShape.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        System.out.println(human2);
        System.out.println(schemaShape.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        System.out.println(human3);
        System.out.println(schemaShape.isValid(human3)); // false

    }
}
