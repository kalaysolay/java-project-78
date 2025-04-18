package hexlet.code;

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
        System.out.println(schema.isValid("hexlet"));// true

        schema.minLength(5);
        System.out.println(schema.isValid("hex"));   // false
        System.out.println("---------------------------");
        System.out.println(schema.isValid("hexlet"));// true

        schema.contains("hex");
        System.out.println("---------------------------");
        System.out.println("Schema. Contains = hex. Testing value = hexlet");
        System.out.println(schema.isValid("hexlet"));// true
        System.out.println("---------------------------");
        System.out.println("Schema. Contains = hex. Testing value = hetlet");
        System.out.println(schema.isValid("hetlet"));// false
    }
}
