package net.gbm.devcenter.billing.application.interfaces.validators.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import net.gbm.devcenter.billing.application.interfaces.validators.ProductRangeValidate;

@ApplicationScoped
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ProductRangeValidator implements ConstraintValidator<ProductRangeValidate, Object[]> {
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        int limit = (int) value[0];
        int skip = (int) value[1];
        if(!assertValidLimitRange(limit)){
            context
                    .buildConstraintViolationWithTemplate("Limit range invalid, must be between 1 and 100")
                    .addParameterNode(0)
                    .addPropertyNode( "limit" )
                    .addConstraintViolation();
            return false;
        }
        if(!assertValidSkipRange(limit, skip)){
            context
                    .buildConstraintViolationWithTemplate("Skip must be in the range of 0 and (100 - limit)")
                    .addParameterNode(1)
                    .addPropertyNode( "skip" )
                    .addConstraintViolation();
            return false;

        }

        return true;
    }

    private boolean assertValidLimitRange(int limit) {
        return limit >= 1 && limit <= 100;
    }

    private boolean assertValidSkipRange(int limit, int skip) {
        return skip >= 0 && skip <= (100 - limit);
    }
}