package com.maersk.assignment.validation;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
import javax.validation.Constraint;  
import javax.validation.Payload;  
 
@Constraint(validatedBy = ContainerSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerSize {
    String message() default "ContainerSize Should be either 20 or 40";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}