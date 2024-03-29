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

    private boolean invalidBuy = false;


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
        model.addAttribute("buyError", invalidBuy);
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

    @RequestMapping("offer/delete/{id}")
    public String deleteOffer(Model model, Principal principal, @PathVariable Long id) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Offer offer = offersService.getOffer(id);

        // Comprobar que el usuario identificado es el que esta tratando de borrar la oferta. en caso de que acceda
        // a través del URL a borrar la oferta de otro usuario.
        if(offer.getUser().getId() == user.getId()) {
            offersService.deleteOffer(id);
        }

        return "redirect:/offer/list";

    }

    @RequestMapping("/offer/buy/{id}")
    public String buyOffer(Model model, @PathVariable Long id, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Offer offer = offersService.getOffer(id);

        invalidBuy = offersService.checkInvalidBuy(offer, user);
        if(!invalidBuy){
            offersService.buyOffer(offer, user);
            usersService.decreaseMoney(user, offer.getPrice());
        }

        return "redirect:/offer/search";
    }

    @RequestMapping("/offer/search/update")
    public String updateList(Model model, Pageable pageable, Principal principal){
        String currentUserMail = principal.getName();
        User user = usersService.getUserByEmail(currentUserMail);
        Page<Offer> offers = offersService.getAllOffers(pageable);
        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("buyError", invalidBuy);
        model.addAttribute("user", user);
        return "offer/search :: tableOffers";
    }
}
