package com.example.demoMODEL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String password;
	
	public Person(){
	}
	
	public Person (String name, String password)
	{
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
