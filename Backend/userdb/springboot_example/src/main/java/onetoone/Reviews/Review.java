package onetoone.Reviews;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import onetoone.Restaurants.Restaurant;
import onetoone.Users.User;

import java.time.LocalDateTime;

@Entity
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identification of this review", name = "id", required = true, value = "test id")
    private Long id;

	@ApiModelProperty(notes = "The comments of someone else", name = "comments", required = true, value = "test comments")
    private String comments;

	@ApiModelProperty(notes = "The time created when the review was created", name = "time Created", required = true, value = "test timeCreated")
    private LocalDateTime timeCreated = LocalDateTime.now();

    @ManyToOne(targetEntity = User.class)
	@ApiModelProperty(notes = "The user who gave the comments", name = "user", required = true, value = "test user")
    private User user;

	@ManyToOne(targetEntity = Restaurant.class)
	@ApiModelProperty(notes = "The restaurant that contain this review", name = "restaurant", required = true, value = "test restaurant")
	private Restaurant restaurant;
	
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

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public LocalDateTime getTimeCreated()
    {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) 
    {
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
