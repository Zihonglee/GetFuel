package com.example.demoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoMODEL.Person;
import com.example.demoSERVICE.PersonService;

@RequestMapping("api/v1/person")
@RestController
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
}
