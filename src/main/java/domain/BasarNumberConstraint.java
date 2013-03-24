package domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=BasarNumberConstraintValidator.class)
public @interface BasarNumberConstraint {
    
    String message() default "{domain.BasarNumberConstraint.message}";
    
    Class<?>[] groups() default { };
    
    Class<? extends Payload>[] payload() default { };

}
