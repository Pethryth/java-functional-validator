package pl.wytworniakodu.commons.validator;

import java.util.Objects;

public class NotBlankValidator extends Validator<String> {


    public NotBlankValidator() {
        super((param) -> Objects.nonNull(param.getValue()) && !param.getValue().isEmpty());
    }

    @Override
    public ValidationResult ok(final ValidationInput param) {
        return () -> true;
    }

    @Override
    public ValidationResult fail(final ValidationInput param) {
        return new ValidationResult() {

            @Override
            public boolean isValid() {
                return false;
            }

            @Override
            public String errorMessage() {
                return param.getFieldName() + " is empty";
            }
        };
    }
}