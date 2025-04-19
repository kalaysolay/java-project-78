package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck(n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(n -> n != null && n >= min && n <= max);
        return this;
    }
}
