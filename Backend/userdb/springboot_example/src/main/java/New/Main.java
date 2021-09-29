package New;





import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import New.Reviews.Review;
import New.Reviews.ReviewRepository;
import New.Users.User;
import New.Users.UserRepository;
import New.Cuisines.Cuisine;
import New.Cuisines.CuisineRepository;

@SpringBootApplication
//@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


//    @Bean
//    CommandLineRunner initUser(ReviewRepository reviewRepository,UserRepository userRepository) {
//        return args -> {
//                        Review review1 = new Review("comments 1");
//                        Review review2 = new Review("comments 2");
//                        User user1 = new User("John", "john@somemail.com","asd");
//                    User user2 = new User("Jane", "jane@somemail.com","asdasd");
//                    User user3 = new User("Justin", "justin@somemail.com","asdasdas");
//                    userRepository.save(user1);
//                    userRepository.save(user2);
//                    userRepository.save(user3);
//
//        };
//    }

//    @Bean
//    CommandLineRunner initUser(UserRepository userRepository) {
//        return args -> {
//                    User user1 = new User("John", "john@somemail.com","asd");
//                    User user2 = new User("Jane", "jane@somemail.com","asdasd");
//                    User user3 = new User("Justin", "justin@somemail.com","asdasdas");
//                    userRepository.save(user1);
//                    userRepository.save(user2);
//                    userRepository.save(user3);
//
//                };
//            }

//    @Bean
//    CommandLineRunner initReview(UserRepository userRepository, ReviewRepository reviewRepository, CuisineRepository cuisineRepository) {
//        return args -> {
//
//            User user1 = new User("John", "john@somemail.com","asd");
//            User user2 = new User("Jane", "jane@somemail.com","asdasd");
//            User user3 = new User("Justin", "justin@somemail.com","asdasdas");
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
//
//            Review review1 = new Review("Comments 1");
//            Review review2 = new Review ("Comments 2");
//            reviewRepository.save(review1);
//            reviewRepository.save(review2);
//
//            Cuisine cuisine1 = new Cuisine("Thai");
//            Cuisine cuisine2 = new Cuisine("Chinese");
//            cuisineRepository.save(cuisine1);
//            cuisineRepository.save(cuisine2);
//
//        };
//    }
}


