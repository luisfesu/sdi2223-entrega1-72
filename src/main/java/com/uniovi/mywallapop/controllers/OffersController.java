package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.*;
import com.uniovi.mywallapop.validators.AddOfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class OffersController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AddOfferValidator addOfferValidator;


    @RequestMapping(value = "/offer/add")
    public String addMark(Model model) {

        model.addAttribute("offer", new Offer());
        model.addAttribute("usersList", usersService.getUsers());

        return "offer/add"; // Plantilla Thymeleaf fragments/templates/offer/add.html
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String addMark(@ModelAttribute Offer offer, BindingResult result, Model model) {
        addOfferValidator.validate(offer, result);

        if(result.hasErrors())  { // Regresamos al formulario
            model.addAttribute("usersList", usersService.getUsers());
            return "offer/add";
        }

        // TODO: Hacerlo con Principal
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userDni = authentication.getName();
        User currentRegisteredUser = usersService.getUserByEmail(userDni);

        offer.setUser(currentRegisteredUser);
        offer.setDate(LocalDateTime.now().toString());

        offersService.addOffer(offer);
        return "redirect:/offer/list";
    }

}
