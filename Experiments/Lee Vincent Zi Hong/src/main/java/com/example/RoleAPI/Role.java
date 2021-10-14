package com.example.RoleAPI;

import com.example.demoAPI.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleType;

    @OneToMany(targetEntity = User.class)
    @JsonIgnore
    private List<User> users;

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

    public List<User> getUsers() 
    {
        return users;
    }

    public void setUsers(List<User> users) 
    {
        this.users = users;
    }
}
