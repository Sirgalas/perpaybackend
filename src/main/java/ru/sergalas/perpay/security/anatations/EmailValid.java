package ru.sergalas.perpay.security.anatations;

import ru.sergalas.perpay.security.validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface EmailValid {
    String message() default "Invalid Email";

    Class<?>[] groups() default{};

    Class<? extends Payload >[] payload() default {};
}
