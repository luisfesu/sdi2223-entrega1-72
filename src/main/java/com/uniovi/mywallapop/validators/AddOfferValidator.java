package com.uniovi.mywallapop.validators;

import com.uniovi.mywallapop.entities.Offer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AddOfferValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Offer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Offer offer = (Offer) target;

        if(offer.getPrice() <= .00) {
            errors.rejectValue("price", "error.addOffer.price");
        }

        if(offer.getTitle().isBlank()) {
            errors.rejectValue("title", "error.addOffer.title.blank");
        }

        if(offer.getTitle().length() > 70) {
            errors.rejectValue("title", "error.addOffer.title.length");
        }

        if(offer.getDescription().isBlank()) {
            errors.rejectValue("description", "error.addOffer.description.blank");
        }

        if(offer.getDescription().length() > 2000) {
            errors.rejectValue("description", "error.addOffer.description.length");
        }

    }
}
