package com.maersk.assignment.validation;

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  
  
public class ContainerSizeValidator implements ConstraintValidator<ContainerSize,Integer> {  
  
    public boolean isValid(Integer containerSize, ConstraintValidatorContext cvc) {  
          
        if(containerSize==20 || containerSize==40) {
        	return true;
        
        }else {
        	return false;
        }
    }  
}
