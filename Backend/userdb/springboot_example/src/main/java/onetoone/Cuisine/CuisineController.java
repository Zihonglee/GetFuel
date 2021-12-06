package onetoone.Cuisine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantController;

@Api(value = "CuisineController", description = "REST APIs related to Cuisine Entity!!!!")
@RestController
@RequestMapping(value = "/cuisines")
public class CuisineController 
{
	@Autowired
	public CuisineRepository cuisineRepository;
	
	@Autowired
	public RestaurantController restController;
	
	@ApiOperation(value = "Get the list of cuisines in the System ", response = Iterable.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping
	public List<Cuisine> getAllCuisine()
	{
		return cuisineRepository.findAll(Sort.by(Sort.Direction.ASC, "cuisineType"));
	}
	
	@GetMapping("/{id}/AllRestaurant")
	public List<Restaurant> getRestaurants(@PathVariable Long id)
	{
		Cuisine cuisineType = cuisineRepository.getCuisineById(id);
		return  cuisineType.getRestaurants();
	}

	@ApiOperation(value = "Get specific cuisine with the given identificatio in the System ", response = Cuisine.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("{id}")
	public Cuisine getCuisineById(@PathVariable Long id)
	{
		return cuisineRepository.getCuisineById(id);
	}

	@ApiOperation(value = "Post a new cuisine in the System ", response = String.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
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

	@ApiOperation(value = "Put and replacing an old cuisine with a new cuisine in the System", response = String.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/{id}")
	public String updateCuisine(@PathVariable Long id, @RequestBody Cuisine request)
	{
		Cuisine cuisine = cuisineRepository.getCuisineById(id);
		if(cuisine == null || request == null)
		{
			return null;
		}
		else
		{
			cuisine.setCuisineType(request.getCuisineType());
			cuisine.setRestaurants(request.getRestaurants());
			cuisineRepository.save(cuisine);
			return "Replacement was successful";
		}
	}

	@ApiOperation(value = "Delete a specific cuisine with given identification in the System ", response = String.class)
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/{id}")
	public String deleteCuisineById(@PathVariable Long id)
	{
		Cuisine cuisine = cuisineRepository.getCuisineById(id);
		if (cuisine == null)
		{
			return "failure";
		}
		else
		{
			if (cuisine.getRestaurants() == null)
			{
				cuisineRepository.deleteCuisineById(id);
				return "Deleted successfully";
			}
			else
			{
				List<Restaurant> listOfRestaurant = cuisine.getRestaurants();
				int size = listOfRestaurant.size();
				for (int i = 0; i < size; ++i)
				{
					restController.deleteRestaurantById(listOfRestaurant.get(0).getId());
				}
				cuisineRepository.deleteCuisineById(id);				
				return "Deleted successfully";
			}
		}
	}
}