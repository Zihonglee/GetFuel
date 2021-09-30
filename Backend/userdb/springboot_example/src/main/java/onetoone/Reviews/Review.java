package onetoone.Reviews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import onetoone.Users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comments;
    private LocalDateTime timecreated = LocalDateTime.now();

    @OneToOne
    @JsonIgnore
    private User user;


    public Review(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(LocalDateTime timecreated) {
        this.timecreated = timecreated;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
