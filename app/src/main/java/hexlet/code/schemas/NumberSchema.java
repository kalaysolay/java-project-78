package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    /**
     * добавляет обаязательность заполнения для схемы.
     *
     * @return текущая схема
     */
    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    /**
     * добавляет проверку на положительное число.
     *
     * @return текущая схема
     */
    public NumberSchema positive() {
        addCheck("positive", n -> n == null || n > 0);
        return this;
    }

    /**
     * добавляет проверку на нахождение в промежутке между двумя числами.
     *
     * @param min - левая граница промежутка
     * @param max - правая граница промежутка
     * @return текщуий объект
     */
    public NumberSchema range(int min, int max) {
        addCheck("range", n -> n != null && n >= min && n <= max);
        return this;
    }
}
