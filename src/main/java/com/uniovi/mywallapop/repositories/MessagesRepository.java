package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Message, Long> {
}
