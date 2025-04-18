package com.athome.validation;

import com.athome.anno.QuestionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionTypeValidation implements ConstraintValidator<QuestionType,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)return true;
        if(s.equals("判断题") || s.equals("单选题") || s.equals("多选题") || s.equals("简答题") || s.equals("填空题")){
            return true;
        }
        return false;
    }
}
