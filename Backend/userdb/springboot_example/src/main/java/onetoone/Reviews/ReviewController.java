package onetoone.Reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/reviews")
public class ReviewController
{
    @Autowired
    public ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAllReview()
    {
        return reviewRepository.findAll();
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
}
