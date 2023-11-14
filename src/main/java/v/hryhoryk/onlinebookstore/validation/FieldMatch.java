package v.hryhoryk.onlinebookstore.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FiledMatchValidator.class)
@Documented
public @interface FieldMatch {
    String firstField();

    String secondField();

    String message() default "Fields must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
