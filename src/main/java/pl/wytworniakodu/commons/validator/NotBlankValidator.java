package pl.wytworniakodu.commons.validator;

import java.util.Objects;

public class NotBlankValidator extends Validator<String> {


    private static final String ERROR_MESSAGE = " is empty";

    public NotBlankValidator() {
        super((param) -> Objects.nonNull(param.getValue()) && !param.getValue().isEmpty());
    }

    @Override
    public ValidationResult ok(final ValidationInput param) {
        return () -> true;
    }


    @Override
    protected String errorMessage(final String fieldName) {
        return fieldName + ERROR_MESSAGE;
    }
}
