package onetoone.Reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantRepository;


@RestController
@RequestMapping(value = "/reviews")
public class ReviewController
{
	@Autowired
	public RestaurantRepository restRepository;
	
	@Autowired
	public ReviewRepository reviewRepository;

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

	@GetMapping("/{id}")
	public Review getReviewById(@PathVariable Long id)
	{
		return reviewRepository.getReviewById(id);
	}

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

	@DeleteMapping("/{id}")
	public String deleteReview(@PathVariable Long id)
	{
		reviewRepository.deleteReviewById(id);
		return "Deleted successfully";
	}

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
