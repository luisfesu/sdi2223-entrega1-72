package com.uniovi.mywallapop.validators;

import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SendMessageValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Message.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Message message = (Message) target;

        if(message.getText().isBlank()){
            errors.rejectValue("text", "error.message.add");
        }
    }
}
