package onetoone.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "UserController", description = "REST APIs related to User Entity!!!!")
@RestController
@RequestMapping (value = "/user")
public class UserController
{
	@Autowired
	public UserRepository userRepository;
	
	@ApiOperation(value = "Post a new user in the System ", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
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
	
	@ApiOperation(value = "Get the list of people in the System ", response = Iterable.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping
	public List<User> getAllPeople()
	{
		return userRepository.findAll();
	}
	
	@ApiOperation(value = "Get specific user with the given identificatio in the System ", response = User.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping ("/{id}")
	public User getPersonById(@PathVariable("id") Long id)
	{
		return userRepository.getUserById(id);
	}
	
	@ApiOperation(value = "Delete a specific user with given identification in the System ", response = String.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/{id}")
	public String deletePersonById(@PathVariable("id") Long id)
	{
		userRepository.deleteById(id);
		return "User deleted";
	}
	
	@ApiOperation(value = "Put and replacing an old user with a new user in the System", response = String.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
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
			person.setTimeCreated(personToUpdate.getTimeCreated());
			person.setRoleType(personToUpdate.getRoleType());
			person.setEmail(personToUpdate.getEmail());
			userRepository.save(person);
			return "Replacement was successful";
		}
	}
	
}
