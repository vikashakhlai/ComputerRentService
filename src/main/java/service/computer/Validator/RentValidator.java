package service.computer.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import service.computer.Models.RentForm;
import service.computer.Models.User;

@Component
public class RentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RentForm computer =(RentForm)o;
        if(computer.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}
