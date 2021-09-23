package com.example.demoAPI;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demoMODEL.Person;
import com.example.demoSERVICE.PersonService;

@RestController
@RequestMapping (path = "api/person")
public class PersonController
{
	private final PersonService personservice;
	
	@Autowired
	public PersonController(PersonService personservice)
	{
		this.personservice = personservice;
	}
	
	@PostMapping
	public void addPerson(@RequestBody Person person)
	{
		personservice.addPerson(person);
	}
	
	@GetMapping
	public ArrayList<Person> getAllPeople()
	{
		return personservice.GetAllPeople();
	}
	
	@GetMapping (path = "{id}")
	public Person getPersonById(@PathVariable("id") UUID id)
	{
		return personservice.getPersonById(id).orElse(null);
	}
	
	@DeleteMapping (path = "{id}")
	public void deletePersonById(@PathVariable("id") UUID id)
	{
		personservice.deletePerson(id);
	}
	
	@PutMapping (path = "{id}")
	public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person personToUpdate)
	{
		personservice.updatePerson(id, personToUpdate);
	}
	
}
