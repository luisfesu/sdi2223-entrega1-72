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
        // create users
        User user1 = new User("pedro@mail.com", "Pedro", "Díaz");
        user1.setPassword("123456");
        user1.setRole(rolesService.getRoles()[0]);

        User user2 = new User("maria@mail.com", "Maria", "García");
        user2.setPassword("123456");
        user2.setRole(rolesService.getRoles()[0]);

        User user3 = new User("juan@mail.com", "Juan", "Pérez");
        user3.setPassword("123456");
        user3.setRole(rolesService.getRoles()[0]);

        User user4 = new User("ana@mail.com", "Ana", "Fernández");
        user4.setPassword("123456");
        user4.setRole(rolesService.getRoles()[0]);

        User user5 = new User("carlos@mail.com", "Carlos", "González");
        user5.setPassword("123456");
        user5.setRole(rolesService.getRoles()[0]);

        User user6 = new User("lucia@mail.com", "Lucia", "Lopez");
        user6.setPassword("123456");
        user6.setRole(rolesService.getRoles()[0]);

        User user7 = new User("daniel@mail.com", "Daniel", "Ruiz");
        user7.setPassword("123456");
        user7.setRole(rolesService.getRoles()[0]);

        User user8 = new User("clara@mail.com", "Clara", "Sánchez");
        user8.setPassword("123456");
        user8.setRole(rolesService.getRoles()[0]);

        User user9 = new User("pablo@mail.com", "Pablo", "Martínez");
        user9.setPassword("123456");
        user9.setRole(rolesService.getRoles()[0]);

        User user10 = new User("sofia@mail.com", "Sofia", "Torres");
        user10.setPassword("123456");
        user10.setRole(rolesService.getRoles()[0]);

        User user11 = new User("admin@email.com", "admin", "admin");
        user11.setPassword("admin");
        user11.setRole(rolesService.getRoles()[1]);

        // add users to repository
        usersService.addUser(user1);
        usersService.addUser(user2);
        usersService.addUser(user3);
        usersService.addUser(user4);
        usersService.addUser(user5);
        usersService.addUser(user6);
        usersService.addUser(user7);
        usersService.addUser(user8);
        usersService.addUser(user9);
        usersService.addUser(user10);

        usersService.addUser(user11);
    }
}
