package com.example.demoAPI;

import java.util.HashMap;
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
		peopleList.put(person.getId(), person);
		return "User saved";
	}
	
	@GetMapping
	public HashMap<UUID, Person> getAllPeople()
	{
		return peopleList;
	}
	
	@GetMapping ("get/{id}")
	public Person getPersonById(@PathVariable("id") UUID id)
	{
		return peopleList.get(id);
	}
	
	@DeleteMapping ("delete/{id}")
	public String deletePersonById(@PathVariable("id") UUID id)
	{
		peopleList.remove(id);
		return "User deleted";
	}
	
	@PutMapping ("reset/{id}")
	public String updatePersonById(@PathVariable("id") UUID id, @RequestBody Person personToUpdate)
	{
		peopleList.replace(id, personToUpdate);
		return "Replacement was successful";
	}
	
}
