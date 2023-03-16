package com.uniovi.mywallapop.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String date;

    private Double price;
    private Boolean buyed = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private User buyer;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private Set<Conversation> conversations;

    public Offer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Boolean getBuyed() {
        return buyed;
    }

    public void setBuyed(Boolean buyed) {
        this.buyed = buyed;
    }

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }
}

