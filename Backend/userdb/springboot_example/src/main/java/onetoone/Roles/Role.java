package com.example.RoleAPI;

import com.example.demoAPI.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
public class Role 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleType;

    @OneToMany(targetEntity = Person.class)
    @JsonIgnore
<<<<<<< HEAD
    private List<Person> users;

    public Role() 
    {
    	roleType = "user";
    }
    
    public Role(String roleType) 
    {
        this.roleType = roleType;
=======
    private List<User> users;


    public Role(String roleType) 
    {
        this.roleType = roleType;
    }
       public Role() {
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
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

<<<<<<< HEAD
    public List<Person> getUsers() 
=======
    public List<User> getUsers() 
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
    {
        return users;
    }

<<<<<<< HEAD
    public void setUsers(List<Person> users) 
=======
    public void setUsers(List<User> users) 
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
    {
        this.users = users;
    }
}
