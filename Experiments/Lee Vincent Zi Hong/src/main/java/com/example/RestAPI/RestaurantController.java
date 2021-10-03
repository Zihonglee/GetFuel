package com.example.RestAPI;

import java.util.HashMap;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

import com.example.RestMODEL.Restaurant;

@RestController
@RequestMapping (path = "api/restaurant")
public class RestaurantController
{
	HashMap<UUID, Restaurant> restaurantList = new HashMap<>();
	
	@PostMapping
	public String addPerson(@RequestBody Restaurant restaurant)
	{
		restaurantList.put(restaurant.getId(), restaurant);
		return "Restaurant saved";
	}
	
	@GetMapping
	public HashMap<UUID, Restaurant> getAllPeople()
	{
		return restaurantList;
	}
	
	@GetMapping (path = "{id}")
	public Restaurant getRestaurantById(@PathVariable("id") UUID id)
	{
		return restaurantList.get(id);
	}
	
	@DeleteMapping (path = "{id}")
	public String deletePersonById(@PathVariable("id") UUID id)
	{
		restaurantList.remove(id);
		return "Restaurant deleted";
	}
	
	@PutMapping (path = "{id}")
	public String updateRestaurantById(@PathVariable("id") UUID id, @RequestBody Restaurant restaurantToUpdate)
	{
		restaurantList.replace(id, restaurantToUpdate);
		return "Replacement was successful";
	}
}
