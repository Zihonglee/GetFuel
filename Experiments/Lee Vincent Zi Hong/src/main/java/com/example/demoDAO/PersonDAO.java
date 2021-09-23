package com.example.demoDAO;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.demoMODEL.Person;

public interface PersonDAO 
{
	public int insertPerson(UUID id, Person person);
	
	public default int insertPerson(Person person)
	{
		UUID id = UUID.randomUUID();
		return insertPerson(id, person);
	}
	
	ArrayList<Person> selectPerson();
	
	public int deletePersonID(UUID id);
	
	public int updatePersonID(UUID id, Person person);
	
	public Optional<Person> selectPersonByid(UUID id);
}
