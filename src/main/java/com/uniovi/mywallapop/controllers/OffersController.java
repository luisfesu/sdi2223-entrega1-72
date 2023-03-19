package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.*;
import com.uniovi.mywallapop.validators.AddOfferValidator;
import com.uniovi.mywallapop.validators.BuyOfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OffersController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AddOfferValidator addOfferValidator;

    @Autowired
    private BuyOfferValidator buyOfferValidator;


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

        String userMail = authentication.getName();
        User currentRegisteredUser = usersService.getUserByEmail(userMail);

        offer.setUser(currentRegisteredUser);
        offer.setDate(LocalDateTime.now().toString());

        offersService.addOffer(offer);
        return "redirect:/offer/list";
    }

    @RequestMapping("/offer/purchased")
    public String getOffersPurchased(Model model, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        List<Offer> offerList = offersService.getOffersByBuyer(user);

        model.addAttribute("offerList", offerList);
        return "offer/purchased";
    }

    @RequestMapping("/offer/search")
    public String getOffersBySearch(Model model, Pageable pageable, Principal principal,
                                   @RequestParam(value = "", required = false) String searchText) {

        Page<Offer> offers;
        String currentUserMail = principal.getName();
        User user = usersService.getUserByEmail(currentUserMail);

        if(searchText != null && !searchText.isEmpty()) {
            offers = offersService.searchOfferByTitle(pageable, searchText);
        } else {
            offers = offersService.getAllOffers(pageable);
        }

        model.addAttribute("offerList", offers.getContent()); // Lista de ofertas
        model.addAttribute("page", offers); // Pagina
        model.addAttribute("user", user);
        return "offer/search";
    }


    @RequestMapping("/offer/list")
    public String getOffersByUser(Model model, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        List<Offer> offers = offersService.getOffersByUser(user);

        model.addAttribute("offerList", offers);
        return "offer/list";
    }

    @RequestMapping("/offer/buy/{id}")
    public String buyOffer(@ModelAttribute Offer ofer, BindingResult result, Model model, Pageable pageable,
                           @PathVariable Long id, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Offer offer = offersService.getOffer(id);

        List<Object> target = new ArrayList<>();
        target.add(offer);
        target.add(user);
        buyOfferValidator.validate(target, result);

        if(result.hasErrors()){
            Page<Offer> offers = offersService.getAllOffers(pageable);
            model.addAttribute("offerList", offers.getContent());
            model.addAttribute("page", offers);
            return "offer/search";
        }

        offersService.buyOffer(offer, user);
        usersService.decreaseMoney(user, offer.getPrice());

        return "redirect:/offer/search";
    }

    @RequestMapping("/offer/search/update")
    public String updateList(Model model, Pageable pageable, Principal principal){
        Page<Offer> offers = offersService.getAllOffers(pageable);
        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);
        return "offer/search :: tableOffers";
    }
}
