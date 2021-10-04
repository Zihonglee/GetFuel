package com.example.RestAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.RestMODEL.Restaurant;

@RestController
@RequestMapping (path = "restaurant")
public class RestaurantController
{
	@Autowired
	restaurantRepository restRepository;
	
	@PostMapping
	public String addRestaurant(@RequestBody Restaurant restaurant)
	{
		if (restaurant == null)
		{
			return "Failure";
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
	
	@GetMapping (path = "{id}")
	public Restaurant getRestaurantById(@PathVariable("id") Long id)
	{
		return restRepository.findRestaurantById(id);
	}
	
	@DeleteMapping (path = "{id}")
	public String deletePersonById(@PathVariable("id") Long id)
	{
		restRepository.deleteRestaurantById(id);
		return "Restaurant deleted";
	}
	
	@PutMapping (path = "{id}")
	public String updateRestaurantById(@PathVariable("id") Long id, @RequestBody Restaurant restaurantToUpdate)
	{
		if (restaurantToUpdate == null)
		{
			return "Failure";
		}
		else
		{
			Restaurant restaurant = restRepository.findRestaurantById(id);
			restaurant = restaurantToUpdate;
			restaurant.setId(id);
			restRepository.save(restaurant);
			return "Replacement was successful";
		}
	}
}
