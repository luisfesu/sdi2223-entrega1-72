package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Message;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    public void addMessage(Conversation conversation, User user, String text){
        Message message = new Message(user, conversation, text, LocalDateTime.now().toString());
        messagesRepository.save(message);
    }

    public List<Message> getMessagesByConversation(Conversation conversation) {
        return messagesRepository.getMessagesByConversation(conversation);
    }
}
