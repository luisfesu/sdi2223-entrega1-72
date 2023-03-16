package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Message, Long> {
    List<Message> getMessagesByConversation(Conversation conversation);
}
