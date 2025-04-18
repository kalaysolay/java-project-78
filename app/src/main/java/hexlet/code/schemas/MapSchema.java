package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>>  {
    public MapSchema required() {
        super.required();
        addCheck(Objects::nonNull); // защита от "не Map"
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck(map -> map != null && map.size() == size);
        return this;
    }
}
