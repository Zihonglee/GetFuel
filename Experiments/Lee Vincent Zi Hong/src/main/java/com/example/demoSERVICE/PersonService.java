package com.example.demoSERVICE;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoDAO.PersonDAO;
import com.example.demoMODEL.Person;

@Service
public class PersonService
{
	private final PersonDAO personDAO;
	
	@Autowired	
	public PersonService(@Qualifier("address") PersonDAO personDAO)
	{
		this.personDAO = personDAO;
	}
	
	public int addPerson(Person person)
	{
		return personDAO.insertPerson(person);
	}
	
	public ArrayList<Person> GetAllPeople()
	{
		return personDAO.selectPerson();	
	}
	
	public Optional<Person> getPersonById(UUID id)
	{
		return personDAO.selectPersonByid(id);
	}
	
	public int deletePerson(UUID id)
	{
		return personDAO.deletePersonID(id);
	}
	
	public int updatePerson(UUID id, Person newPerson)
	{
		return personDAO.updatePersonID(id, newPerson);
	}
}
