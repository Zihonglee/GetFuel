package com.example.demoAPI;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.UUID;
=======
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c1e1b52a41a2c7af3fb91af7ba724eca97f25288
import org.springframework.web.bind.annotation.*;

import com.example.demoMODEL.Person;

@RestController
@RequestMapping (path = "api/person")
public class PersonController
{
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
	
<<<<<<< HEAD
=======
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
	
>>>>>>> c1e1b52a41a2c7af3fb91af7ba724eca97f25288
}
