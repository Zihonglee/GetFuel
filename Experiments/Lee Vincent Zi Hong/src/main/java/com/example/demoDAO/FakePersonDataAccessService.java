package com.example.demoDAO;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demoMODEL.Person;

@Repository("address")
public class FakePersonDataAccessService implements PersonDAO
{
	private static ArrayList<Person> database = new ArrayList<>();
	@Override
	public int insertPerson(UUID id, Person person) 
	{
		database.add(new Person(id, person.getName()));
		return 1;
	}
	@Override
	public ArrayList<Person> selectPerson() 
	{
		return database;
	}	
}
