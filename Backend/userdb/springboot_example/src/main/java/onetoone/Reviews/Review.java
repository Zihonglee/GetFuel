package onetoone.Reviews;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import onetoone.Restaurants.Restaurant;

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

	@ApiModelProperty(notes = "The user who gave the comments", name = "user", required = true, value = "test user")
    private String user;
	
	@ApiModelProperty(notes = "The iditification of user who gave the comments", name = "user", required = true, value = "test userId")
	private Long userId;

	@ManyToOne(targetEntity = Restaurant.class, cascade = CascadeType.ALL)
	@JsonIgnore
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
    
    public String getUser() 
    {
        return user;
    }
    
    public void setUser(String user) 
    {
        this.user = user;
    }
    
    public void setUser(String user, Long userId) 
    {
        this.user = user;
        this.userId = userId;
    }
    
    public Long getUserId()
    {
    	return userId;
    }
    
    public void setRestaurant(Restaurant rest)
    {
    	restaurant = rest;
    }
    
    public Restaurant getRestaurant()
    {
    	return restaurant;
    }
}
