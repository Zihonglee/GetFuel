package com.example.ReviewsAPI;

<<<<<<< HEAD
import javax.persistence.*;

=======
<<<<<<< HEAD
import com.example.demoAPI.Person;

import javax.persistence.*;
import java.time.LocalDateTime;
=======
>>>>>>> d6fff4d9101be906ed18cbf242c239fcacc7d5fd
import onetoone.Users.User;

import java.time.LocalDateTime;

>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2

@Entity
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;
    private LocalDateTime timeCreated = LocalDateTime.now();

<<<<<<< HEAD
    @ManyToOne(targetEntity = User.class)
=======
<<<<<<< HEAD
    @ManyToOne(targetEntity = Person.class)
    private Person user;
=======
    @ManyToOne
      @JoinColumn(name = "user_id")
>>>>>>> d6fff4d9101be906ed18cbf242c239fcacc7d5fd
    private User user;
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2

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

<<<<<<< HEAD
    public Person getUser() 
=======
    public User getUser() 
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
    {
        return user;
    }

<<<<<<< HEAD
    public void setUser(Person user) 
=======
    public void setUser(User user) 
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
    {
        this.user = user;
    }
}
