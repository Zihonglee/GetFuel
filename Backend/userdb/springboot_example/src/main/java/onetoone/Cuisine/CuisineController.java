package onetoone.Cuisine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController 
{
	@Autowired
	public CuisineRepository cuisineRepository;

	@GetMapping
	public List<Cuisine> getAllCuisine()
	{
		return cuisineRepository.findAll(Sort.by(Sort.Direction.ASC, "cuisineType"));

	}

	@GetMapping("{id}")
	public Cuisine getCuisineById(@PathVariable Long id)
	{
		return cuisineRepository.getCuisineById(id);
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
		Cuisine cuisine = cuisineRepository.getCuisineById(id);
		if(cuisine == null || request == null)
		{
			return null;
		}
		else
		{
			cuisineRepository.getCuisineById(id).setCuisineType(request.getCuisineType());
			cuisineRepository.getCuisineById(id).setRestaurants(request.getRestaurants());
			return cuisineRepository.getCuisineById(id);
		}
	}


	@DeleteMapping("/{id}")
	public String deleteCuisineById(@PathVariable Long id)
	{
		cuisineRepository.deleteCuisineById(id);
		return "Deleted successfully";
	}
}
