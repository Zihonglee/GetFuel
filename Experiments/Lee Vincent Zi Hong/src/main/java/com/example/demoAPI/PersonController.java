package com.example.demoAPI;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demoMODEL.Person;

@RestController
@RequestMapping (path = "api/person")
public class PersonController
{
	@Autowired
	personRepository userRepository;
	
	HashMap<UUID, Person> peopleList = new HashMap<>();
	
	@PostMapping
	public String addPerson(@RequestBody Person person)
	{
		if (person == null)
		{
			return "Failure";
		}
		else
		{
			userRepository.save(person);
			return "User saved";
		}
	}
	
	@GetMapping
	public List<Person> getAllPeople()
	{
		return userRepository.findAll();
	}
	
	@GetMapping ("get/{id}")
	public Person getPersonById(@PathVariable("id") UUID id)
	{
		return userRepository.getById(id);
	}
	
	@DeleteMapping ("delete/{id}")
	public String deletePersonById(@PathVariable("id") UUID id)
	{
		userRepository.deleteById(id);
		return "User deleted";
	}
	
	@PutMapping ("reset/{id}")
	public String updatePersonById(@PathVariable("id") UUID id, @RequestBody Person personToUpdate)
	{
		Person person = userRepository.getById(id);
		
		if (personToUpdate == null)
		{
			return "Failure";
		}
		userRepository.save(personToUpdate);
		return "Replacement was successful";
	}
	
}
