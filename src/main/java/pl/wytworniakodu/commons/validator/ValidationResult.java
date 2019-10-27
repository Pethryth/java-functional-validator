package pl.wytworniakodu.commons.validator;

import java.util.function.Supplier;

public interface ValidationResult {

    boolean isValid();

    default String errorMessage() {
        return "";
    }

    default void ifInvalid(EmptyValidationConsumer consumer) {
        if (!isValid()) {
            consumer.accept();
        }
    }

    default void ifValid(EmptyValidationConsumer consumer) {
        if (isValid()) {
            consumer.accept();
        }
    }
}