package pl.wytworniakodu.commons.validator;


import lombok.Value;

@Value
public class ValidationInput<VALUE> {

    private VALUE value;

    String fieldName;
}
