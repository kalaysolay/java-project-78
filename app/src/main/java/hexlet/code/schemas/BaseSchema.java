package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    private boolean required = false;

    /**
     * добавляет валидатору обязательность.
     *
     * @return текущий объект
     */

    public  BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    /**
     * добавляет в карту проверок указанное ограничение как ключ и предикат.
     *
     * @param name - имя проверки
     * @param check - предикат
     */
    protected void addCheck(String name, Predicate<T> check) {
        checks.put(name, check); // если имя уже есть — просто заменит
    }

    /**
     * проверяет валидность схемы.
     *
     * @param value - переданное значение для проверки
     * @return - результат проверки, булевое
     */
    public boolean isValid(T value) {
        if (!required && value == null) {
            return true;
        }
        if (required && value == null) {
            return false;
        }
        for (Predicate<T> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод нужен посмотреть, какие проверки применяются (для отладки).
     *
     * @return - копия мапы с проверками
     */
    public Map<String, Predicate<T>> getChecks() {
        return Map.copyOf(checks);
    }
}
