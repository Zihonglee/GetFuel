package onetoone.Reviews;

import onetoone.Users.User;
import onetoone.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success Review\"}";
    private String failure = "{\"message\":\"failure Review\"}";

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

        User user = userRepository.findByReview_Id(id);
        user.setReview(null);
        userRepository.save(user);

        reviewRepository.deleteById(id);
        return success;
    }

}
