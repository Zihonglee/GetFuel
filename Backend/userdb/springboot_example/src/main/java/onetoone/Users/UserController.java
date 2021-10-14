package com.example.demoAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/user")
<<<<<<< HEAD
public class PersonController
{
	@Autowired
	public personRepository userRepository;
	
	@PostMapping
	public String addPerson(@RequestBody Person person)
=======
public class UserController
{
	
	@Autowired
	public UserRepository userRepository;
	
	@PostMapping
	public String addPerson(@RequestBody User person)
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
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
<<<<<<< HEAD
	public List<Person> getAllPeople()
=======
	public List<User> getAllPeople()
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
	{
		return userRepository.findAll();
	}
	
	@GetMapping ("/{id}")
<<<<<<< HEAD
	public Person getPersonById(@PathVariable("id") Long id)
=======
	public User getPersonById(@PathVariable("id") Long id)
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
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
<<<<<<< HEAD
	public String updatePersonById(@PathVariable("id") Long id, @RequestBody Person personToUpdate)
=======
	public String updatePersonById(@PathVariable("id") Long id, @RequestBody User personToUpdate)
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
	{
		if (personToUpdate == null)
		{
			return "Failure";
		}
		else
		{
<<<<<<< HEAD
			Person person = userRepository.findPersonById(id);
=======
			User person = userRepository.findPersonById(id);
>>>>>>> 691355e29cd7e52f9061ad10839e9e993ae823e2
			person = personToUpdate;
			person.setId(id);
			userRepository.save(person);
			return "Replacement was successful";
		}
	}
	
}
