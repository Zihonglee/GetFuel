package onetoone.Restaurants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import onetoone.Cuisine.CuisineRepository;

@RestController
@RequestMapping (value = "/restaurant")
public class RestaurantController
{
	@Autowired
	public RestaurantRepository restRepository;

//    @Autowired
//    public CuisineRepository cuisineRepository;
	
	@PostMapping
	public String addRestaurant(@RequestBody Restaurant restaurant)
	{
		if (restaurant == null)
		{
			return "failure";
		}
		else
		{
			restRepository.save(restaurant);
			return "Restaurant saved";
		}
	}
	
	@GetMapping
	public List<Restaurant> getAllRestaurant()
	{
		return restRepository.findAll();
	}
	
	@GetMapping ("/{id}")
	public Restaurant getRestaurantById(@PathVariable("id") Long id)
	{
		return restRepository.getRestaurantById(id);
	}
	
	@DeleteMapping ("/{id}")
	public String deleteRestaurantById(@PathVariable("id") Long id)
	{
		restRepository.deleteRestaurantById(id);
		return "Restaurant deleted";
	}
	
	@PutMapping ("/{id}")
	public String updateRestaurantById(@PathVariable("id") Long id, @RequestBody Restaurant restaurantToUpdate)
	{
		Restaurant restaurant = restRepository.getRestaurantById(id);
		if (restaurant == null || restaurantToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			restRepository.getRestaurantById(id).setName(restaurantToUpdate.getName());
			restRepository.getRestaurantById(id).setPrice(restaurantToUpdate.getPrice());
			restRepository.getRestaurantById(id).setRating(restaurantToUpdate.getRating());
			restRepository.getRestaurantById(id).setCuisine(restaurantToUpdate.getCuisine());
			restRepository.getRestaurantById(id).setReviews(restaurantToUpdate.getReviews());
			restRepository.getRestaurantById(id).setUrl(restaurantToUpdate.getUrl());
			return "Replacement was successful";
		}
	}
}
