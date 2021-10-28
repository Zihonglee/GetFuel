package onetoone.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/user")
public class UserController
{
	@Autowired
	public UserRepository userRepository;
	
	@PostMapping
	public String addPerson(@RequestBody User person)
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
	public List<User> getAllPeople()
	{
		return userRepository.findAll();
	}
	
	@GetMapping ("/{id}")
	public User getPersonById(@PathVariable("id") Long id)
	{
		return userRepository.getUserById(id);
	}
	
	@DeleteMapping ("/{id}")
	public String deletePersonById(@PathVariable("id") Long id)
	{
		userRepository.deleteById(id);
		return "User deleted";
	}
	
	@PutMapping ("/{id}")
	public String updatePersonById(@PathVariable("id") Long id, @RequestBody User personToUpdate)
	{
		User person = userRepository.getUserById(id);
		if (person == null || personToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			userRepository.getUserById(id).setTimeCreated(personToUpdate.getTimeCreated());
			userRepository.getUserById(id).setRoleType(personToUpdate.getRoleType());
			userRepository.getUserById(id).setEmail(personToUpdate.getEmail());

			return "Replacement was successful";
		}
	}
	
}
