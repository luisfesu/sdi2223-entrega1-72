package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.ConversationsService;
import com.uniovi.mywallapop.services.MessagesService;
import com.uniovi.mywallapop.services.OffersService;
import com.uniovi.mywallapop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private MessagesService messagesService;

    @RequestMapping(value = "/conversation/add/{id}", method = RequestMethod.GET)
    private String getConversation(Model model, @PathVariable Long id, Principal principal){
        Offer offer = offersService.getOffer(id);
        User seller = offer.getUser();

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        Conversation conversation = conversationsService.createConversation(seller, user, offer);

        return "redirect:/conversation/details/" + conversation.getId();
    }

    @RequestMapping(value = "conversation/message/add/{id}", method = RequestMethod.POST)
    private String uploadMessage(Model model, @PathVariable Long id, Principal principal,
                                 @RequestParam String messageText){

        User user = usersService.getUserByEmail(principal.getName());

        Conversation conversation = conversationsService.getConversation(id);
        messagesService.addMessage(conversation,user, messageText);

        return "redirect:/conversation/details/" + conversation.getId();
    }

    @RequestMapping(value = "conversation/list")
    private String getConversationsList(Model model, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        List<Conversation> conversations = conversationsService.getConversationsByUser(user);
        model.addAttribute("conversationList", conversations);
        return "conversation/list";
    }

    @RequestMapping(value = "conversation/details/{id}")
    public String getConversation(Model model, @PathVariable Long id){
        Conversation conversation = conversationsService.getConversation(id);
        List<Message> messages = messagesService.getMessagesByConversation(conversation);

        model.addAttribute("messageList", messages);
        model.addAttribute("offer", conversation.getOffer());
        model.addAttribute("conversation", conversation);

        return "conversation/messages";
    }

    @RequestMapping(value = "conversation/details/{id}/update")
    public String updateConversation(Model model, @PathVariable Long id){
        Conversation conversation = conversationsService.getConversation(id);
        List<Message> messages = messagesService.getMessagesByConversation(conversation);

        model.addAttribute("messageList", messages);
        return "conversation/messages :: tableMessages";
    }

}
