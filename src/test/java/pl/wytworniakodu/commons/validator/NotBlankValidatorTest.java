package pl.wytworniakodu.commons.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotBlankValidatorTest {


    @Test
    void shouldReturnErrorMessage() {
        ValidationResult result = new NotBlankValidator().test(new ValidationInput<>("", "name"));
        Assertions.assertFalse(result.isValid());
        Assertions.assertEquals(result.errorMessage(), "name is empty");
    }

    @Test
    void shouldReturnValidResult() {
        ValidationResult result = new NotBlankValidator().test(new ValidationInput<>("Wytwornia Kodu", "name"));
        Assertions.assertTrue(result.isValid());
        Assertions.assertEquals(result.errorMessage(), "");
    }
}