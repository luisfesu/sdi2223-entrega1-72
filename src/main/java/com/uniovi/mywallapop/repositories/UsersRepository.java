package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
