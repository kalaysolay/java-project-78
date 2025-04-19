package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();
    private boolean required = false;

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    public boolean isValid(T value) {
        if (!required && (value == null)) {
            return true;
        }
        if (required && value == null) {
            return false;
        }
        for (Predicate<T> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
