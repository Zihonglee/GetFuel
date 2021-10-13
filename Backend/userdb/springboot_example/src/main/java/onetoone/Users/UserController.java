package com.example.demoAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/user")
public class PersonController
{
	@Autowired
	public personRepository userRepository;
	
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
	
	@GetMapping ("/{id}")
	public Person getPersonById(@PathVariable("id") Long id)
	{
		return userRepository.findPersonById(id);
	}
	
	@DeleteMapping ("/{id}")
	public String deletePersonById(@PathVariable("id") String id)
	{
		userRepository.deleteById(id);
		return "User deleted";
	}
	
	@PutMapping ("/{id}")
	public String updatePersonById(@PathVariable("id") Long id, @RequestBody Person personToUpdate)
	{
		if (personToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			Person person = userRepository.findPersonById(id);
			person = personToUpdate;
			person.setId(id);
			userRepository.save(person);
			return "Replacement was successful";
		}
	}
	
}
