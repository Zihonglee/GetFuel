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
    public ReviewRepository reviewRepository;
    
    @Autowired
    public RestaurantRepository restRepository;

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
    
    @PutMapping("/review/{restaurantId}")
    public String assignReviews(@PathVariable Long restaurantId, @RequestParam Review reviewByUser)
    {
		Restaurant restaurant = restRepository.getRestaurantById(restaurantId);
		if(restaurant == null)
		{
			return "failure";
		}
		else
		{
			List<Review> getall = restaurant.getReviews();
			getall.add(reviewByUser);
			restaurant.setReviews(getall);
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

    //delete review method
}
