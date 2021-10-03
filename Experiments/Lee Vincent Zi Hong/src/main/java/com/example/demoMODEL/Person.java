package com.example.demoMODEL;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person
{
	@Id
	@Column(name="id")
	private final UUID id;
	@Column(name="name")
	private final String name;
	@Column(name="pasword")
	private final String password;
	
	public Person (UUID id, String name, String password)
	{
		this.id = UUID.randomUUID();;
		this.name = name;
		this.password = password;
	}
	
	public UUID getId()
	{
		return id;
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
