package pl.wytworniakodu.commons.validator;

@FunctionalInterface
public interface ValidatorFunction<PARAM> {

    ValidationResult test(ValidationInput<PARAM> param);

    default ValidatorFunction<PARAM> and(ValidatorFunction<PARAM> other) {
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
                public String errorMessage() {
                    return firstResult.errorMessage() + "\n" + secondResult.errorMessage();
                }
            };
        };
    }

    default ValidatorFunction<PARAM> or(ValidatorFunction<PARAM> other) {
        return (param) -> {
            ValidationResult firstResult = this.test(param);
            return firstResult.isValid() ? firstResult : other.test(param);
        };
    }
}