package onetoone.Reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantRepository;

@Api(value = "ReviewController", description = "REST APIs related to review Entity!!!!")
@RestController
@RequestMapping(value = "/reviews")
public class ReviewController
{
	@Autowired
	public RestaurantRepository restRepository;
	
	@Autowired
	public ReviewRepository reviewRepository;

	@ApiOperation(value = "Get the list of reviews in the System ", response = Iterable.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping
	public List<Review> getAllReview()
	{
		if (reviewRepository.findAll() == null)
		{
			return null;
		}
		else
		{
			return reviewRepository.findAll();
		}
	}

	@ApiOperation(value = "Get the review by the given idntification in the System ", response = Review.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/{id}")
	public Review getReviewById(@PathVariable Long id)
	{
		return reviewRepository.getReviewById(id);
	}

	@ApiOperation(value = "Post a new review in the System ", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping
	public String createReview(@RequestBody Review review)
	{
		if (review == null)
		{
			return "failure";
		}
		else
		{
			reviewRepository.save(review);
			return "success";
		}
	}

	@ApiOperation(value = "Post a new review to the specific restaurant with given the identification of restaurantn in the System ", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/review/{restaurantId}")
	public String assignReviews(@PathVariable Long restaurantId, @RequestBody Review reviewByUser)
	{
		Restaurant restaurant = restRepository.getRestaurantById(restaurantId);
		if(restaurant == null)
		{
			return "failure";
		}
		else
		{
			restaurant.addReviews(reviewByUser);
			createReview(reviewByUser);
			restRepository.save(restaurant);
			return "success";
		}
	}
	
	@ApiOperation(value = "Put and replacing an old review with a new review in the System", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping("/{id}")
	public String updateReview(@PathVariable Long id, @RequestBody Review request)
	{
		Review review = reviewRepository.getReviewById(id);
		if(review == null || request == null)
		{
			return null;
		}
		else
		{
			review.setComments(request.getComments());
			review.setTimeCreated(request.getTimeCreated());
			review.setUser(request.getUser());
			reviewRepository.save(review);
			return "Replacement was successful";
		}
	}

	@ApiOperation(value = "Delete a specific review with the given identification in the System", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id)
	{
		reviewRepository.deleteReviewById(id);
		return "Deleted successfully";
	}

	@ApiOperation(value = "Delete a review located in the specific restaurant in the System", response = String.class)
	@ApiResponses(value = { 
	            @ApiResponse(code = 200, message = "Success|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"), 
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	@DeleteMapping("/{restaurantId}/review/{reviewId}")
	public String deleteReviewRestaurant(@PathVariable Long restaurantId, @PathVariable Long reviewId)
	{
		Restaurant rest = restRepository.getRestaurantById(restaurantId);
		Review review = reviewRepository.getById(reviewId);
		List<Review> getall = rest.getReviews();
		getall.remove(review);
		reviewRepository.deleteReviewById(reviewId);
		restRepository.save(rest);
		return "Delete success";
	}
}
