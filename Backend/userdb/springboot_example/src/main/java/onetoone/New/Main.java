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
        SpringApplication.run(onetoone.Main.class, args);
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

    @Bean
    CommandLineRunner initReview(ReviewRepository reviewRepository) {
        return args -> {
            Review review1 = new Review("Comments 1");
            Review review2 = new Review ("Comments 2");
            reviewRepository.save(review1);
            reviewRepository.save(review2);

        };
    }
}


