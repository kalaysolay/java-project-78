package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String>  {
    /**
     * добавляет обаязательность заполнения для схемы.
     *
     * @return текущая схема
     */
    @Override
    public StringSchema required() {
        super.required();
        addCheck("requiredString", s -> s != null && !s.isEmpty());
        return this;
    }

    /**
     * добавляет проверку на минимальную длину строки.
     *
     * @param length - проверяемая длина строки
     * @return - текущая схема
     */
    public StringSchema minLength(int length) {
        addCheck("minLength", s -> s == null || s.length() >= length);
        return this;
    }

    /**
     * добавляет проверку на вхождение подстроки в схеме.
     *
     * @param substring - подстрока для проверки
     * @return - текущая схема
     */
    public StringSchema contains(String substring) {
        addCheck("contains", s -> s == null || s.contains(substring));
        return this;
    }
}
