package pl.wytworniakodu.commons.validator;

@FunctionalInterface
public interface ValidatorFunction<PARAM> {

    ValidationResult test(ValidationInput<PARAM> param);

    default ValidatorFunction<PARAM> and(final ValidatorFunction<PARAM> other) {
        return (param) -> {
            ValidationResult firstResult = this.test(param);
            if (firstResult.isValid()) {
                return other.test(param);
            }
            ValidationResult secondResult = other.test(param);
            if (secondResult.isValid()) {
                return firstResult;
            }
            return new ValidationResult() {
                @Override
                public boolean isValid() {
                    return false;
                }

                @Override
                public String validatorErrorMessage() {
                    return firstResult.validatorErrorMessage() + "\n" + secondResult.validatorErrorMessage();
                }
            };
        };
    }

    default ValidatorFunction<PARAM> or(final ValidatorFunction<PARAM> other) {
        return (param) -> {
            ValidationResult firstResult = this.test(param);
            return firstResult.isValid() ? firstResult : other.test(param);
        };
    }
}
