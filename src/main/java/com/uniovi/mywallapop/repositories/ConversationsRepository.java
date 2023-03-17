package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.Conversation;
import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationsRepository extends CrudRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c WHERE c.userA = ?1 AND c.userB = ?2 AND c.offer = ?3")
    Conversation getConversationBySellerAndUser(User seller, User user, Offer offer);

    @Query("SELECT DISTINCT c FROM Conversation c WHERE c.userA = ?1 OR c.userB = ?1")
    List<Conversation> getConversationByUser(User user);
}
