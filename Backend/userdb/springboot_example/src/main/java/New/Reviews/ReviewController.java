package New.Reviews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/reviews")
    List<Review> getAllReview(){
        return reviewRepository.findAll();
    }

    @GetMapping(path = "/reviews/{id}")
    Review getReviewById( @PathVariable int id){
        return reviewRepository.findById(id);
    }

    @PostMapping(path = "/reviews")
    String createReview(@RequestBody Review review){
        if (review == null)
            return failure;
        reviewRepository.save(review);
        return success;
    }

    @PutMapping("/reviews/{id}")
    Review updateReview(@PathVariable int id, @RequestBody Review request){
        Review review = reviewRepository.findById(id);
        if(review == null)
            return null;
        reviewRepository.save(request);
        return reviewRepository.findById(id);
    }


    @DeleteMapping(path = "/reviews/{id}")
    String deleteReview(@PathVariable int id){
        reviewRepository.deleteById(id);
        return success;
    }
}
