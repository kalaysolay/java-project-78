package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>>  {
    @SuppressWarnings("S1450")
    private Map<String, BaseSchema<String>> shapeSchemas;

    /**
     * добавляет обаязательность заполнения для схемы.
     *
     * @return текущий объект
     */
    @Override
    public MapSchema required() {
        super.required();
        addCheck("isMap", Objects::nonNull);
        return this;
    }

    /**
     * возвращает размер.
     *
     * @param size
     * @return текущий объект
     */
    public MapSchema sizeof(int size) {
        addCheck("sizeof", map -> map != null && map.size() == size);
        return this;
    }

    /**
     * метод для вложенных условий.
     *
     * @param schemas - схема валидации
     */
    public void shape(Map<String, BaseSchema<String>> schemas) {
        this.shapeSchemas = schemas;

        addCheck("shape", map -> {
            if (map == null) {
                return true;
            }

            for (Map.Entry<String, BaseSchema<String>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> fieldSchema = entry.getValue();
                String value = (String) map.get(key);

                if (!fieldSchema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
    }


}
