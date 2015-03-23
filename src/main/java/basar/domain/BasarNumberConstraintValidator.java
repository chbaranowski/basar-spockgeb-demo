package basar.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import basar.data.User;

public class BasarNumberConstraintValidator implements ConstraintValidator<BasarNumberConstraint, String> {

    @Autowired
    Basar basar;
    
    @Override
    public void initialize(BasarNumberConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String basarNumber, ConstraintValidatorContext context) {
        User user = basar.findByBasarNumber(basarNumber);
        if(user == null) {
            context.buildConstraintViolationWithTemplate("The basar number " + basarNumber + " does not exist in the repository.");
            return false;
        } else {
            return true;
        }
        
    }

}
