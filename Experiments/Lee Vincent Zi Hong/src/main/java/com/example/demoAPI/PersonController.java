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
	
	@GetMapping ("{id}")
	public Person getPersonById(@PathVariable("id") String id)
	{
		return userRepository.findPersonById(id);
	}
	
	@DeleteMapping ("{id}")
	public String deletePersonById(@PathVariable("id") String id)
	{
		userRepository.deleteById(id);
		return "User deleted";
	}
	
	@PutMapping ("{id}")
	public String updatePersonById(@PathVariable("id") String id, @RequestBody Person personToUpdate)
	{
		if (personToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			Person person = userRepository.findPersonById(id);
			System.out.println(person);
			person = personToUpdate;
			person.setId(id);
			userRepository.save(person);
			return "Replacement was successful";
		}
	}
	
}
