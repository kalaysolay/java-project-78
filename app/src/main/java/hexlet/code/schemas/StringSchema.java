package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringSchema {
    // храню проверки в этом списке
    private final List<Predicate<String>> checks = new ArrayList<>();
    private boolean isRequired = false;

    /*
    переделал под предикат
    private int minLength;
    private String containsValue;
*/
    public StringSchema required() {
        isRequired = true;
        checks.add(s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        checks.add(s -> s != null && s.length() >= min);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.add(s -> s != null && s.contains(substring));
        return this;
    }

    public boolean isValid(String value) {
        if (!isRequired && (value == null || value.isEmpty())) {
            return true;
        }
        for (Predicate<String> check : checks) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
