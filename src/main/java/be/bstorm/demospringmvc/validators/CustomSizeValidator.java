package be.bstorm.demospringmvc.validators;

import be.bstorm.demospringmvc.validators.annotations.CustomSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomSizeValidator implements ConstraintValidator<CustomSize,String> {

    private int min;
    private int max;

    @Override
    public void initialize(CustomSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String v, ConstraintValidatorContext constraintValidatorContext) {

        if(v == null || v.trim().isEmpty()){
            return true;
        }

        return v.length() >= min && v.length() <= max;
    }
}
