package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class InsertSampleDataService {

    @Autowired  private UsersService usersService;
    @Autowired  private RolesService rolesService;
    @Autowired private OffersService offersService;

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

        User user2 = new User("miguel@mail.com", "Miguel", "Perez");
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

        User user11 = new User("maria@mail.com", "Maria", "García");
        user11.setPassword("123456");
        user11.setRole(rolesService.getRoles()[0]);

        User user12 = new User("admin@email.com", "admin", "admin");
        user12.setPassword("admin");
        user12.setRole(rolesService.getRoles()[1]);

        // add users to repository


        usersService.addUser(user1);
        usersService.addUser(user2);
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

        usersService.addUser(user12);


        Offer offer1 = new Offer("oferta 1", "esta es la oferta 1, del usuario 1",100.0, LocalDateTime.now().toString());
        Offer offer2 = new Offer("oferta 2", "esta es la oferta 2, del usuario 1",100.0, LocalDateTime.now().toString());
        Offer offer3 = new Offer("oferta 3", "esta es la oferta 3, del usuario 1",100.0, LocalDateTime.now().toString());
        Offer offer4 = new Offer("oferta 4", "esta es la oferta 4, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer5 = new Offer("oferta 5", "esta es la oferta 5, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer6 = new Offer("oferta 6", "esta es la oferta 6, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer7 = new Offer("oferta 7", "esta es la oferta 7, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer8 = new Offer("oferta 8", "esta es la oferta 8, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer9 = new Offer("oferta 9", "esta es la oferta 9, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer10 = new Offer("oferta 10", "esta es la oferta 10, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer11 = new Offer("oferta 11", "esta es la oferta 11, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer12 = new Offer("oferta 12", "esta es la oferta 12, del usuario 2",100.0, LocalDateTime.now().toString());
        Offer offer13 = new Offer("oferta 13", "esta es la oferta 13, del usuario 2",100.0, LocalDateTime.now().toString());

        // Tres ofertas del usuario 1
        offer1.setUser(user1);
        offer2.setUser(user1);
        offer3.setUser(user1);

        // 10 ofertas del usuario 2
        offer4.setUser(user2);
        offer5.setUser(user2);
        offer6.setUser(user2);
        offer7.setUser(user2);
        offer8.setUser(user2);
        offer9.setUser(user2);
        offer10.setUser(user2);
        offer11.setUser(user2);
        offer12.setUser(user2);
        offer13.setUser(user2);



        offersService.addOffer(offer1);
        offersService.addOffer(offer2);
        offersService.addOffer(offer3);
        offersService.addOffer(offer4);
        offersService.addOffer(offer5);
        offersService.addOffer(offer6);
        offersService.addOffer(offer7);
        offersService.addOffer(offer8);
        offersService.addOffer(offer9);
        offersService.addOffer(offer10);
        offersService.addOffer(offer11);
        offersService.addOffer(offer12);
        offersService.addOffer(offer13);



    }
}
