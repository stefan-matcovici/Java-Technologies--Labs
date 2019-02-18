package ro.uaic.info.javatechnologies.optcourses.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumeratedValidator.class)
public @interface Enumerated
{
    String message() default "{enumerated}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}