package pl.wytworniakodu.commons.validator;

import java.util.Objects;

public class NotNullValidator extends Validator<Object> {


    public NotNullValidator() {
        super((param) -> Objects.nonNull(param.getValue()));
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
                return param.getFieldName() + " is null";
            }
        };
    }
}