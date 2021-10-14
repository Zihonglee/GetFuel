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
        return reviewRepository.findReviewById(id);
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
    public Review updateReview(@PathVariable Long id, @RequestBody Review request)
    {
        Review review = reviewRepository.findReviewById(id);
        if(review == null)
        {
        	return null;
        }
        else
        {
        	reviewRepository.save(request);
        	return reviewRepository.findReviewById(id);
        }
    }


    @DeleteMapping("/{id}")
    String deleteReview(@PathVariable Long id)
    {
        reviewRepository.deleteReviewById(id);
        return "Deleted successfully";
    }
}
