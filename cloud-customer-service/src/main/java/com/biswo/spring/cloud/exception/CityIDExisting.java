package com.biswo.spring.cloud.exception;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = CityIDExistingValidator.class)
public @interface CityIDExisting {

	String message() default "{CityIDExisting}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
