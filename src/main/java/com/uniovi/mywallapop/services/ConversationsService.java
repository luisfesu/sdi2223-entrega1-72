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
    private MessagesService messagesService;

    public Conversation createConversation(User seller, User user, Offer offer) {
        Conversation conversation = new Conversation(seller, user, offer);
        conversationsRepository.save(conversation);
        return conversation;
    }

    public Conversation getConversationByUserSellerAndOffer(User seller, User user, Offer offer){
        return conversationsRepository.getConversationBySellerAndUser(seller, user, offer);
    }

    public List<Conversation> getConversationsByUser(User user){
        return conversationsRepository.getConversationByUser(user);
    }

    public Conversation getConversation(Long id) {
        return conversationsRepository.findById(id).get();
    }
}
