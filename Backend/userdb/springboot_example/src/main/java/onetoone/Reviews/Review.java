package onetoone.Reviews;


import com.fasterxml.jackson.annotation.JsonIgnore;
import onetoone.Restaurants.Restaurant;
import onetoone.Users.User;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comments;
    private LocalDateTime timeCreated = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;

    public Review(String comments) {
        this.comments = comments;

    }

    public Review() {
    }



    public int getId(){
        return id;
    }

    public void setId(int id ){
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
