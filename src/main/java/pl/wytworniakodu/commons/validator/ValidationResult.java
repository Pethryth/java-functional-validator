package pl.wytworniakodu.commons.validator;

import java.util.function.Consumer;

public interface ValidationResult {

    boolean isValid();

    default String validatorErrorMessage() {
        return "";
    }

    default void ifInvalid(final Consumer<ValidationResult> consumer) {
        if (!isValid()) {
            consumer.accept(this);
        }
    }

    default void ifValid(final Consumer<ValidationResult> consumer) {
        if (isValid()) {
            consumer.accept(this);
        }
    }
}
