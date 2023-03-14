package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.Conversation;
import org.springframework.data.repository.CrudRepository;

public interface ConversationsRepository extends CrudRepository<Conversation, Long> {
}
