package pl.wytworniakodu.commons.validator;


import lombok.Value;

@Value
public class ValidationInput<VALUE> {

    private VALUE value;

    String fieldName;

    public static <VALUE> ValidationInput<VALUE> of(final VALUE value, final String fieldName) {
        return new ValidationInput<>(value, fieldName);
    }
}
