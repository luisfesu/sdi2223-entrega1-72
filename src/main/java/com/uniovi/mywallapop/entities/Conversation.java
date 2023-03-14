package com.uniovi.mywallapop.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User userA;

    @ManyToOne
    private User userB;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private Set<Message> messages;

    public Conversation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public User getUserB() {
        return userB;
    }

    public void setUserB(User userB) {
        this.userB = userB;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
