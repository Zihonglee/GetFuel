package onetoone.Users;

import java.util.List;

import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    private String sameEmail = "{\"message\":\"EmailSame\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        if ( userRepository.findByEmail(user.getEmail())!=null){
            return sameEmail;
        }
        userRepository.save(user);
        return success;
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PutMapping("/users/{userId}/reviews/{reviewid}")
    String assignLaptopToUser(@PathVariable int userId,@PathVariable int reviewid){
        User user = userRepository.findById(userId);
        Review review = reviewRepository.findById(reviewid);
        if(user == null || review == null)
            return failure;
        review.setUser(user);
        user.setReview(review);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
}