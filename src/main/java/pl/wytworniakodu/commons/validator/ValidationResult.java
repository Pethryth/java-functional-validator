package pl.wytworniakodu.commons.validator;

public interface ValidationResult {

    boolean isValid();

    default String validatorErrorMessage() {
        return "";
    }

    default void ifInvalid(final ValidationConsumer consumer) {
        if (!isValid()) {
            consumer.accept();
        }
    }

    default void ifValid(final ValidationConsumer consumer) {
        if (isValid()) {
            consumer.accept();
        }
    }
}
