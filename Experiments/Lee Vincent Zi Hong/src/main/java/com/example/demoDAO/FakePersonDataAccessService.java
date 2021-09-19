package com.example.demoDAO;

import java.util.ArrayList;
import java.util.Optional;
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

	@Override
	public int deletePersonID(UUID id)
	{
		Optional<Person> personcheck = selectPersonByid(id);
		if (personcheck.isEmpty())
		{
			return 0;
		}
		else
		{
			database.remove(personcheck.get());
			return 1;
		}
	}

	@Override
	public int updatePersonID(UUID id, Person personToUpdate) 
	{
		return selectPersonByid(id).map(OriginalPerson -> 
		{
			int indexpersondelete = database.indexOf(OriginalPerson);
			if (indexpersondelete >= 0)
			{
				database.set(indexpersondelete, new Person(id, personToUpdate.getName()));
				return 1;
			}
			else
			{
				return 0;
			}
		}).orElse(0);
	}

	@Override //study and check on this method again
	public Optional<Person> selectPersonByid(UUID id)
	{
		return database.stream().filter(person->person.getId().equals(id)).findFirst();
	}	
}
