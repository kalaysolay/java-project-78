package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String>  {
    // храню проверки в этом списке
    //убираем в базовый класс
    // private final List<Predicate<String>> checks = new ArrayList<>();
   // private boolean isRequired = false;

    /*
    переделал под предикат
    private int minLength;
    private String containsValue;
*/
    private Integer minLength = null;

    @Override
    public StringSchema required() {
        super.required();
        addCheck("requiredString", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s == null || s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", s -> s == null || s.contains(substring));
        return this;
    }
}
