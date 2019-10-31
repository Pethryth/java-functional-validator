package pl.wytworniakodu.commons.validator;

import java.util.Objects;

public class NotNullValidator extends Validator<Object> {

    private static final String ERROR_MESSAGE = " is null";


    public NotNullValidator() {
        super((param) -> Objects.nonNull(param.getValue()));
    }

    @Override
    protected String errorMessage(final String fieldName) {
        return fieldName + ERROR_MESSAGE;
    }
}
