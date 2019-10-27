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

    public abstract ValidationResult ok(ValidationInput<K> param);

    public abstract ValidationResult fail(ValidationInput<K> param);

}