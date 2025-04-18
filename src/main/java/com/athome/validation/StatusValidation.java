package com.athome.validation;

import com.athome.anno.QuestionType;
import com.athome.anno.Status;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidation implements ConstraintValidator<Status,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)return true;
        if(s.equals("待定") || s.equals("已发布") || s.isEmpty()){
            return true;
        }
        return false;
    }
}
