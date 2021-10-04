package com.example.RoleAPI;

import com.example.demoAPI.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleType;

    @OneToMany
    @JsonIgnore
    private List<Person> users;

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

    public List<Person> getUsers() 
    {
        return users;
    }

    public void setUsers(List<Person> users) 
    {
        this.users = users;
    }
}
