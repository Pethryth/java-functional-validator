package pl.wytworniakodu.commons.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotNullValidatorTest {


    @Test
    void shouldReturnErrorMessage() {
        ValidationResult result = new NotNullValidator().test(new ValidationInput<>(null, "name"));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.validatorErrorMessage(), "name is null");
    }

    @Test
    void shouldReturnValidResult() {
        ValidationResult result = new NotNullValidator().test(new ValidationInput<>("Wytwornia Kodu", "name"));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.validatorErrorMessage(), "");
    }
}
