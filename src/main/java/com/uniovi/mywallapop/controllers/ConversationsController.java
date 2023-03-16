package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.ConversationsService;
import com.uniovi.mywallapop.services.OffersService;
import com.uniovi.mywallapop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ConversationsController {

    @Autowired
    private ConversationsService conversationsService;

    @Autowired
    private OffersService offersService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/conversation/{id}", method = RequestMethod.GET)
    private String getConversation(Model model, @PathVariable Long id, Principal principal){
        Offer offer = offersService.getOffer(id);
        User seller = offer.getUser();

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        List<Message> messages = conversationsService.getMessages(seller, user, offer);
        model.addAttribute("messageList", messages);
        return "conversation/messages";
    }

}
