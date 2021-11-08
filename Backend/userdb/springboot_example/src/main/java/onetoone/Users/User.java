package onetoone.Users;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import onetoone.Reviews.Review;

@Entity
public class User
{
	@OneToMany(targetEntity = Review.class, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Identification of this user", name = "id", required = true, value = "test id")
	private Long id;

	@ApiModelProperty(notes = "The name of this user", name = "name", required = true, value = "test name")
	private String name;

	@ApiModelProperty(notes = "The email of this user", name = "email", required = true, value = "test email")
    private String email;

	@ApiModelProperty(notes = "The password of this user", name = "password", required = true, value = "test password")
	private String password;
	
	@ApiModelProperty(notes = "The roleType of this user", name = "role type", required = true, value = "test roleType")
	private String roleType;

	@ApiModelProperty(notes = "The time when the this user accoutn was created", name = "time created", required = true, value = "test timeCreated")
    private LocalDateTime timeCreated = LocalDateTime.now();

	public User(){
	}
	
	public User (String name, String email, String password, String roleType)
	{
		this.email = email;
		this.name = name;
		this.password = password;
		this.roleType= roleType;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType)
	{
		this.roleType = roleType;
	}

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}

    public LocalDateTime getTimeCreated() 
    {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated)
    {
        this.timeCreated = timeCreated;
    }
    
	public String getName()
	{
		return name;
	}	

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
	
	public String getPassword()
	{
		return password;
	}

	public void addReview(Review reviewByUser)
	{
		reviews.add(reviewByUser);
	}
	
	public List<Review> getAllReviews()
	{
		return reviews;
	}
	
	public void deletereview(Review review)
	{
		reviews.remove(review);
	}
	
	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n password: " + getPassword();  
    }
}
