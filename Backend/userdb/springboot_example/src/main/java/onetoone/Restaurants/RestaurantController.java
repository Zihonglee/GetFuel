package onetoone.Restaurants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewController;
import onetoone.Cuisine.Cuisine;

@Api(value = "RestaurantController", description = "REST APIs related to restaurant Entity!!!!")
@RestController
@RequestMapping (value = "/restaurant")
public class RestaurantController
{
	@Autowired
	public RestaurantRepository restRepository;

	@Autowired
	public CuisineRepository cuisineRepository;

	@Autowired
	public ReviewController reviewController;

	@Autowired
	public RestaurantController restController;

	@ApiOperation(value = "Post a new restaurant in the System ", response = String.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping
	public String addRestaurant(@RequestBody Restaurant restaurant)
	{
		if (restaurant == null)
		{
			return "failure";
		}
		else
		{
			if (restaurant.getCuisine() == null)
			{
				restRepository.save(restaurant);
				return "Restaurant saved";
			}
			else
			{
				assigneCusinetoRest(restaurant.getId(), restaurant.getCuisine().getId());
				return "Restaurant saved";
			}
		}
	}

	@ApiOperation(value = "Get all restaurant in the System ", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping
	public List<Restaurant> getAllRestaurant()
	{
		return restRepository.findAll();
	}

	@ApiOperation(value = "Get a specific restaurant with the given identification in the System ", response = Restaurant.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping ("/{id}")
	public Restaurant getRestaurantById(@PathVariable Long id)
	{
		return restRepository.getRestaurantById(id);
	}

	@ApiOperation(value = "Delete a specific restaurant in the System ", response = String.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping ("/{id}")
	public String deleteRestaurantById(@PathVariable Long id)
	{
		Cuisine cuisine = restRepository.getRestaurantById(id).getCuisine();
		Restaurant restaurant = restRepository.getRestaurantById(id);
		if (cuisine == null && restaurant == null)
		{
			return "failure";
		}
		else
		{
			if (cuisine == null && restaurant.getReviews() == null)
			{
				restRepository.deleteRestaurantById(id);
				return "Restaurant deleted";
			}
			else
			{
				if (cuisine == null)
				{
					List<Review> reviews = restaurant.getReviews();
					int size = reviews.size();
					for (int i = 0; i < size; ++i)
					{
						reviewController.deleteReview(reviews.get(0).getId());
					}
					restRepository.deleteRestaurantById(id);
					return "Restaurant deleted";
				}
				else
				{
					List<Review> reviews = restaurant.getReviews();
					int size = reviews.size();
					for (int i = 0; i < size; ++i)
					{
						reviewController.deleteReview(reviews.get(0).getId());
					}
					restaurant.getCuisine().setCuisineType(null);
					List<Restaurant> list = cuisine.getRestaurants();
					list.remove(restaurant);
					cuisine.setRestaurants(list);
					cuisineRepository.save(cuisine);
					restRepository.deleteRestaurantById(id);
					return "Restaurant deleted";
				}
			}
		}
	}

	@ApiOperation(value = "Put a restaurant to a specific cusiine in the System ", response = String.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/{restaurantsId}/cuisine/{cuisineId}")
	public String assigneCusinetoRest(@PathVariable Long restaurantsId, @PathVariable Long cuisineId)
	{
		Restaurant restaurant = restRepository.getRestaurantById(restaurantsId);
		Cuisine cuisine = cuisineRepository.getCuisineById(cuisineId);
		if(restaurant == null || cuisine == null)
		{
			return "failure";
		}
		else
		{
			if (restaurant.getCuisine() != null)
			{					
				Restaurant store = restaurant;
				Cuisine cuisine2 = restaurant.getCuisine();
				List<Restaurant> restaurantsList = cuisine2.getRestaurants();
				restaurantsList.remove(store);
				cuisineRepository.save(cuisine2);

				store = new Restaurant(store.getName(), store.getPrice(), store.getRating(), cuisine, store.getUrl());
				cuisine.getRestaurants().add(restaurant);
				updateRestaurantById(restaurantsId, store);
				cuisineRepository.save(cuisine);
				return "success";					
			}
			else
			{
				cuisine.getRestaurants().add(restaurant);
				restaurant.setCuisine(cuisine);
				restRepository.save(restaurant);
				cuisineRepository.save(cuisine);
				return "success";
			}
		}
	}

	@ApiOperation(value = "Put and replacing an old restaurant with a new restaurant in the System ", response = String.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
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
