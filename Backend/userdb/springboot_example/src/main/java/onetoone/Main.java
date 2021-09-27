package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import onetoone.Users.User;
import onetoone.Users.UserRepository;
import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewRepository;


@SpringBootApplication
//@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    CommandLineRunner intiReview(UserRepository userRepository, ReviewRepository reviewRepository) {
        return args -> {

            User user1 = new User("cheehau","cheehau@gmail.com","1234");
            User user2 = new User("cheehau2","cheehau+2@gmail.com", "12345");
            Review review1 = new Review("This is nice restaraunt, I like");
            Review review2 = new Review("I am loving it");
            user1.setReview(review1);
            user2.setReview(review2);
            userRepository.save(user1);
            userRepository.save(user2);



        };
    }

}
