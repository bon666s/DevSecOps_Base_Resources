package net.gbm.devcenter.billing.application.interfaces.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import net.gbm.devcenter.billing.application.interfaces.validators.impl.ProductRangeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductRangeValidator.class)
public @interface ProductRangeValidate {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}