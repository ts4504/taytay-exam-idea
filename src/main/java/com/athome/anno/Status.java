package com.athome.anno;

import com.athome.validation.QuestionTypeValidation;
import com.athome.validation.StatusValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StatusValidation.class})
public @interface Status {
    String message() default "发布类型只能是：待定、已发布";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
