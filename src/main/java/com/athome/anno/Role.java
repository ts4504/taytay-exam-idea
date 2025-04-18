package com.athome.anno;

import com.athome.validation.RoleValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoleValidation.class})
public @interface Role {
    String message() default "只能是 student/teacher/admin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
