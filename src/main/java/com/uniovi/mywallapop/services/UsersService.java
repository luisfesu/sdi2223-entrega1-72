package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.*;
import com.uniovi.mywallapop.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }
    //public void addUser(User user) {
//        usersRepository.save(user);
//    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void editUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
