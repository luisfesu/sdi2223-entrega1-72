package com.uniovi.mywallapop.validators;

import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class BuyOfferValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Offer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<Object> objects = (List<Object>) target;
        Offer offer = (Offer) objects.get(0);
        User user = (User) objects.get(1);

        if(offer.getPrice() > user.getMoney()){
            errors.rejectValue("price", "error.buy.nomoney");
        }
        if(offer.getBuyed()){
            errors.rejectValue("buyed", "error.buy.buyed");
        }
        if(offer.getUser().equals(user)){
            errors.rejectValue("user", "error.buy.user");
        }
    }
}
