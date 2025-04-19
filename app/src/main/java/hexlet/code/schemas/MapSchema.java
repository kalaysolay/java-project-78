package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>>  {
    private Map<String, BaseSchema<String>> shapeSchemas;

    public MapSchema required() {
        super.required();
        addCheck(Objects::nonNull); // защита от "не Map"
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(map -> map != null && map.size() == size);
        return this;
    }

    public void shape(Map<String, BaseSchema<String>> schemas) {
        this.shapeSchemas = schemas;

        addCheck(map -> {
            if (map == null) {
                return true;
            }
            for (var entry : shapeSchemas.entrySet()) {
                var key = entry.getKey();
                var schema = entry.getValue();
                String value = (String) map.get(key); // всё ещё знаем, что value — строка
                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
    }


}
