package pl.wytworniakodu.commons.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValidatorTest {

    private static final String FIELD_NAME = "fieldName";

    @Test
    void testAndShouldReturnFalseIfNotPassedValidations() {
        ValidationResult result = new NotBlankValidator()
                .and(new NoWhiteSpacesValidator())
                .and(new CapitalLettersValidator())
                .test(new ValidationInput<>("a b c", FIELD_NAME));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(FIELD_NAME + NoWhiteSpacesValidator.IS_NOT_PASSED_NO_WHITE_SPACES_VALIDATOR + "\n"
                        + FIELD_NAME + CapitalLettersValidator.IS_NOT_PASSED_CAPITAL_LETTERS_VALIDATOR,
                result.validatorErrorMessage());
    }

    @Test
    void testAndShouldReturnTrueIfPassedValidations() {
        ValidationResult result = new NotBlankValidator()
                .and(new NoWhiteSpacesValidator())
                .and(new CapitalLettersValidator())
                .test(new ValidationInput<>("A B C", FIELD_NAME));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(FIELD_NAME + NoWhiteSpacesValidator.IS_NOT_PASSED_NO_WHITE_SPACES_VALIDATOR,
                result.validatorErrorMessage());
    }

    @Test
    void testOrShouldReturnTrueIfPassedSomeValidations() {
        ValidationResult result = new NoWhiteSpacesValidator()
                .or(new CapitalLettersValidator())
                .test(new ValidationInput<>("A B C", FIELD_NAME));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals("", result.validatorErrorMessage());
    }

    @Test
    void testOrShouldReturnFalseIfNotPassedAnyValidations() {
        ValidationResult result = new NoWhiteSpacesValidator()
                .or(new CapitalLettersValidator())
                .test(new ValidationInput<>("a b c", FIELD_NAME));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(FIELD_NAME + CapitalLettersValidator.IS_NOT_PASSED_CAPITAL_LETTERS_VALIDATOR,
                result.validatorErrorMessage());
    }

    @Test
    void testCombinedOrAndValidationShouldReturnFalse() {
        ValidationResult result = new NoWhiteSpacesValidator()
                .and(new SmallLettersValidator())
                .or(new CapitalLettersValidator())
                .and(new NoWhiteSpacesValidator())
                .test(new ValidationInput<>("A B C", FIELD_NAME));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(FIELD_NAME + NoWhiteSpacesValidator.IS_NOT_PASSED_NO_WHITE_SPACES_VALIDATOR,
                result.validatorErrorMessage());
    }

    @Test
    void testCombinedOrAndValidationShouldReturnTrue() {
        ValidationResult result = new NoWhiteSpacesValidator()
                .and(new SmallLettersValidator())
                .or(new CapitalLettersValidator())
                .and(new NoWhiteSpacesValidator())
                .test(new ValidationInput<>("ABC", FIELD_NAME));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals("", result.validatorErrorMessage());
    }
}


class NoWhiteSpacesValidator extends Validator<String> {


    static final String IS_NOT_PASSED_NO_WHITE_SPACES_VALIDATOR = " is not passed NoWhiteSpacesValidator";

    NoWhiteSpacesValidator() {
        super((param) -> {
            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(param.getValue());
            return !matcher.find();
        });
    }


    @Override
    protected String errorMessage(final String fieldName) {
        return fieldName + IS_NOT_PASSED_NO_WHITE_SPACES_VALIDATOR;
    }
}

class CapitalLettersValidator extends Validator<String> {


    static final String IS_NOT_PASSED_CAPITAL_LETTERS_VALIDATOR = " is not passed CapitalLettersValidator";

    CapitalLettersValidator() {
        super((param) -> {
            Pattern pattern = Pattern.compile("[A-Z]");
            Matcher matcher = pattern.matcher(param.getValue().trim());
            return matcher.find();
        });
    }

    @Override
    public String errorMessage(final String fieldName) {
        return fieldName + IS_NOT_PASSED_CAPITAL_LETTERS_VALIDATOR;
    }
}

class SmallLettersValidator extends Validator<String> {


    private static final String IS_NOT_PASSED_SMALL_LETTERS_VALIDATOR = " is not passed SmallLettersValidator";

    SmallLettersValidator() {
        super((param) -> {
            Pattern pattern = Pattern.compile("[a-z]");
            Matcher matcher = pattern.matcher(param.getValue().trim());
            return matcher.find();
        });
    }

    @Override
    protected String errorMessage(final String fieldName) {
        return fieldName + IS_NOT_PASSED_SMALL_LETTERS_VALIDATOR;
    }
}
