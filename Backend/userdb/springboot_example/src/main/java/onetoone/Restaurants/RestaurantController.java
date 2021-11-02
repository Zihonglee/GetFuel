package onetoone.Restaurants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import onetoone.Cuisine.CuisineRepository;
import onetoone.Cuisine.Cuisine;


@RestController
@RequestMapping (value = "/restaurant")
public class RestaurantController
{
	@Autowired
	public RestaurantRepository restRepository;

	@Autowired
	public CuisineRepository cuisineRepository;

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
	public Restaurant getRestaurantById(@PathVariable Long id)
	{
		return restRepository.getRestaurantById(id);
	}

	@DeleteMapping ("/{id}")
	public String deleteRestaurantById(@PathVariable Long id)
	{
		restRepository.deleteRestaurantById(id);
		return "Restaurant deleted";
	}

	@PutMapping("/{restid}/cuisine/{cuisineid}")
	String assigneCusinetoRest(@PathVariable Long restId, @PathVariable Long cusineId)
	{
		Restaurant restaurant = restRepository.getRestaurantById(restId);
		Cuisine cuisine = cuisineRepository.getCuisineById(cusineId);
		if(restaurant == null || cuisine == null)
		{
			return "failure";
		}
		else
		{
			List<Restaurant> getall = cuisine.getRestaurants();
			getall.add(restaurant);
			cuisine.setRestaurants(getall);
			restaurant.setCuisine(cuisine);
			restRepository.save(restaurant);
			cuisineRepository.save(cuisine);
			return "success";
		}
	}

	@PutMapping ("/{id}")
	public String updateRestaurantById(@PathVariable Long id, @RequestBody Restaurant restaurantToUpdate)
	{
		Restaurant restaurant = restRepository.getRestaurantById(id);
		if (restaurant == null || restaurantToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			restaurant.setName(restaurantToUpdate.getName());
			restaurant.setPrice(restaurantToUpdate.getPrice());
			restaurant.setRating(restaurantToUpdate.getRating());
			restaurant.setCuisine(restaurantToUpdate.getCuisine());
			restaurant.setReviews(restaurantToUpdate.getReviews());
			restaurant.setUrl(restaurantToUpdate.getUrl());
			restRepository.save(restaurant);
			return "Replacement was successful";
		}
	}
}
