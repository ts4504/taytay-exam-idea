package com.athome.validation;

import com.athome.anno.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidation implements ConstraintValidator<Role,String> {

    /**
     * @param s 要校验的数据
     * @param constraintValidatorContext
     * @return true通过，false不通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验
        if(s == null)return false;
        if(s.equals("student") || s.equals("teacher") || s.equals("admin")){
            return true;
        }
        return false;
    }
}
