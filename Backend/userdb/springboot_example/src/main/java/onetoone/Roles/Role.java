package onetoone.Roles;

import javax.persistence.*;

@Entity
public class Role 
{
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleType;


    public Role() 
    {
    	roleType = "user";
    }

    public Role(String roleType) 
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

    public String getRoleType() 
    {
        return roleType;
    }

    public void setRoleType(String roleType) 
    {
        this.roleType = roleType;
    }


}
