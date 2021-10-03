package com.example.demoMODEL;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="pasword")
	private String password;
	
	public Person(){
	}
	public Person (String name, String password)
	{
		UUID idrandom = UUID.randomUUID();
		this.id = idrandom.toString();
		this.name = name;
		this.password = password;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
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
