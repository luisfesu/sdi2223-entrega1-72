package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.ConversationsService;
import com.uniovi.mywallapop.services.MessagesService;
import com.uniovi.mywallapop.services.OffersService;
import com.uniovi.mywallapop.services.UsersService;
import com.uniovi.mywallapop.validators.SendMessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private SendMessageValidator sendMessageValidator;

    @RequestMapping(value = "/conversation/add/{id}", method = RequestMethod.GET)
    private String getConversation(Model model, @PathVariable Long id, Principal principal){
        Offer offer = offersService.getOffer(id);
        User seller = offer.getUser();

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        if(seller.equals(user)){
            return "redirect:/offer/search";
        }

        Conversation conversation = conversationsService.getConversationByUserSellerAndOffer(seller, user, offer);

        if(conversation != null){
            return "redirect:/conversation/details/" + conversation.getId();
        }

        conversation = conversationsService.createConversation(seller, user, offer);

        return "redirect:/conversation/details/" + conversation.getId();
    }

    @RequestMapping(value = "conversation/message/add/{id}", method = RequestMethod.POST)
    private String uploadMessage(@Validated Message message, BindingResult result, Model model,
                                 @PathVariable Long id, Principal principal){

        sendMessageValidator.validate(message, result);
        if(result.hasErrors()){
            Conversation conversation = conversationsService.getConversation(id);
            List<Message> messages = messagesService.getMessagesByConversation(conversation);

            model.addAttribute("messageList", messages);
            model.addAttribute("offer", conversation.getOffer());
            model.addAttribute("conversation", conversation);
            return "conversation/messages";
        }


        User user = usersService.getUserByEmail(principal.getName());


        Conversation conversation = conversationsService.getConversation(id);

        if(user.equals(conversation.getUserA()) || user.equals(conversation.getUserB())){
            messagesService.addMessage(conversation,user, message.getText());

            return "redirect:/conversation/details/" + conversation.getId();
        }

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
        model.addAttribute("message", new Message());

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
