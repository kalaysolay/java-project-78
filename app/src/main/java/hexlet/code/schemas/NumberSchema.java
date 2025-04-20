package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", n -> n != null && n >= min && n <= max);
        return this;
    }
}
