package com.athome.anno;

import com.athome.validation.QuestionTypeValidation;
import com.athome.validation.RoleValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {QuestionTypeValidation.class})
public @interface QuestionType {
    String message() default "题目类型只能是：判断题、单选题、多选题、填空题、简答题";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
