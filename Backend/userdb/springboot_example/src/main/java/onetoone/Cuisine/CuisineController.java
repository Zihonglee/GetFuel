package onetoone.Cuisine;

import java.util.List;

//import onetomany.Restaurants.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController 
{
	@Autowired
	public CuisineRepository cuisineRepository;

	@GetMapping
	public List<Cuisine> getAllCuisine()
	{
		return cuisineRepository.findAll();
	}

	@GetMapping("{id}")
	public Cuisine getCuisineById(@PathVariable Long id)
	{
		return cuisineRepository.findCuisineById(id);
	}

	@PostMapping
	public String createCuisine(@RequestBody Cuisine cuisine)
	{
		if (cuisine == null)
		{
			return "failure";
		}
		else
		{
			cuisineRepository.save(cuisine);
			return "success";
		}
	}

	@PutMapping("/{id}")
	public Cuisine updateCuisine(@PathVariable Long id, @RequestBody Cuisine request)
	{
		Cuisine cuisine = cuisineRepository.findCuisineById(id);
		if(cuisine == null)
		{
			return null;
		}
		else
		{
			cuisineRepository.save(request);
			return cuisineRepository.findCuisineById(id);
		}
	}


	@DeleteMapping("/{id}")
	public String deleteCuisine(@PathVariable Long id)
	{
		cuisineRepository.deleteCuisineById(id);
		return "Deleted successfully";
	}
}
