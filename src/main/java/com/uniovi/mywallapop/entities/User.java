package com.uniovi.mywallapop.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String lastName;

    private String password;
    @Column(unique = true)
    private String email;
    @Transient //propiedad que no se almacena en la tabla.
    private String passwordConfirm;

    private Double money = 100.00;

    private String role;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<Offer> purchasedOffers;

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    public User(String email, String name, String lastName) {
        super();
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public User() { }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getEmail() {return email; }
    public void setEmail(String dni) { this.email = dni; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public String getFullName() {
        return this.name + " " + this.lastName;
    }
}
