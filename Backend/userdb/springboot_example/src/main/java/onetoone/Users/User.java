package onetoone.User;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import onetoone.Role;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String email;
	private String password;
    private LocalDateTime timeCreated = LocalDateTime.now();
	

    @ManyToOne(targetEntity = Role.class)
    private Role role;
    
	public User(){
	}
	
	public User (String name, String email, String password)
	{
		role = new Role();
		this.email = email;
		this.name = name;
		this.password = password;
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

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role) 
    {
        this.role = role;
    }
	
	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n password: " + getPassword();  
    }

}
