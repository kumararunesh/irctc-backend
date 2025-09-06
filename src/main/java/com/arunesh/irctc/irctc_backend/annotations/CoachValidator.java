package com.arunesh.irctc.irctc_backend.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoachValidator implements ConstraintValidator<ValidCoach,Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        //validation logic
        System.out.println("Validating Coach number");
        if(value>100)
        {
            return true;
        }
        return false;
    }
}
