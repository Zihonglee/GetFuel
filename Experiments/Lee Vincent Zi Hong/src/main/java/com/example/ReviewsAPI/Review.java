package com.example.ReviewsAPI;

import com.example.demoAPI.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;
    private LocalDateTime timeCreated = LocalDateTime.now();

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Review(String comments) 
    {
        this.comments = comments;
    }

    public Review(){
    }
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() 
    {
        return user;
    }

    public void setUser(User user) 
    {
        this.user = user;
    }
}
