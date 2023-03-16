package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
