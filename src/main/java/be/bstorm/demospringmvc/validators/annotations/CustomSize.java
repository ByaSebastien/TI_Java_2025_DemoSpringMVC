package be.bstorm.demospringmvc.validators.annotations;

import be.bstorm.demospringmvc.validators.CustomSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomSizeValidator.class)
public @interface CustomSize {

    String message() default "WTF";
    int min() default 1;
    int max() default 255;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
