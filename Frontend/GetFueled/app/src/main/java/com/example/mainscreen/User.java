package com.example.mainscreen;

public class User
{
    public String username;
    public String id;
    public String role;

    public User()
    {

    }

    public User(String username, String id, String role)
    {

    }

    public String getUserName()
    {
        return username;
    }

    public void setUserName(String username)
    {
        this.username = username;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
