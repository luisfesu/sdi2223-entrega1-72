package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertSampleDataService {

    @Autowired  private UsersService usersService;
    @Autowired  private RolesService rolesService;

    @PostConstruct
    public void init() {
        // Comentar y descomentar para añadir la sampleData o no
       insertSampleData();
    }

    private void insertSampleData() {
        User user1 = new User("pedro@mail.com", "Pedro", "Díaz");
        user1.setPassword("123456");
        user1.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user1);
    }
}
