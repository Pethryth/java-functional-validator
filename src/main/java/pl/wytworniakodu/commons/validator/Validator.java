package pl.wytworniakodu.commons.validator;

import java.util.function.Predicate;

public abstract class Validator<K> implements ValidatorFunction<K> {

    private Predicate<ValidationInput<K>> predicate;

    protected Validator(final Predicate<ValidationInput<K>> predicate) {
        this.predicate = predicate;
    }

    @Override
    public ValidationResult test(final ValidationInput<K> param) {
        return predicate.test(param) ? ok(param) : fail(param);
    }

    public ValidationResult ok(final ValidationInput<K> param) {
        return () -> true;
    }

    public ValidationResult fail(final ValidationInput<K> param) {
        return new ValidationResult() {

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public String validatorErrorMessage() {
                return errorMessage(param.getFieldName());
            }
        };
    }

    protected abstract String errorMessage(String fieldName);

}
