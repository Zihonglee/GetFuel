package onetoone.Users;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import onetoone.Roles.Role;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String email;
	private String password;
	private String roleType;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
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

	
	@Override
	public String toString()
	{
        return "id: " + getId() + ",/n name: " + getName() + "/n password: " + getPassword();  
    }
}
