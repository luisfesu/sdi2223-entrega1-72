package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.repositories.ConversationsRepository;
import com.uniovi.mywallapop.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationsService {

    @Autowired
    private ConversationsRepository conversationsRepository;

    @Autowired
    private MessagesRepository messagesRepository;



    public List<Message> getMessages(User seller, User user, Offer offer) {
        List<Message> messages = new ArrayList<>();
        Conversation conversation = conversationsRepository.getConversationBySellerAndUser(seller, user, offer);
        if(conversation == null){
            createConversation(seller, user, offer);
            return messages;
        }

        messages = messagesRepository.getMessagesByConversation(conversation);
        return messages;
    }

    private void createConversation(User seller, User user, Offer offer) {
        Conversation conversation = new Conversation(seller, user, offer);
        conversationsRepository.save(conversation);
    }
}
