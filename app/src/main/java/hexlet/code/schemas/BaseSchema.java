package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    private boolean required = false;

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected void addCheck(String name, Predicate<T> check) {
        checks.put(name, check); // если имя уже есть — просто заменит
    }

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

    // Чтобы посмотреть, какие проверки применяются (для отладки)
    public Map<String, Predicate<T>> getChecks() {
        return Map.copyOf(checks);
    }
}
