package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.OffersService;
import com.uniovi.mywallapop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class OffersController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/offer/purchased")
    public String getOffersPurchased(Model model, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        List<Offer> offerList = offersService.getOffersByBuyer(user);

        model.addAttribute("offerList", offerList);
        return "offer/purchased";
    }
}
