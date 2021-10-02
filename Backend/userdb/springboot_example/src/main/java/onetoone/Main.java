package onetoone;



import onetoone.Cuisine.Cuisine;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Roles.Role;
import onetoone.Roles.RoleRespository;
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
    CommandLineRunner initUser(ReviewRepository reviewRepository,UserRepository userRepository, CuisineRepository cuisineRepository,RoleRespository roleRespository,RestaurantRepository restaurantRepository ) {
        return args -> {
                        Review review1 = new Review("comments 1");
                        Review review2 = new Review("comments 2");
                        User user1 = new User("John", "john@somemail.com","asd");
                        User user2 = new User("Jane", "jane@somemail.com","asdasd");
                        User user3 = new User("Justin", "justin@somemail.com","asdasdas");
                        Cuisine cuisine1 = new Cuisine("Chinese");
                        Cuisine cuisine2 = new Cuisine ("American");
                        Role role1 = new Role("Admin");
                        Role role2 = new Role ("User");
                        Role role3 = new Role ("Support");
                        Restaurant rest1 = new Restaurant("Blaze pizza","10","3");
                        Restaurant rest2 = new Restaurant ("bovid","123","rating 0");
                        Restaurant rest3 = new Restaurant ("taste","12332","12");
                    userRepository.save(user1);
                    userRepository.save(user2);
                    userRepository.save(user3);
                    reviewRepository.save(review1);
                    reviewRepository.save(review2);
                    cuisineRepository.save(cuisine1);
                    cuisineRepository.save(cuisine2);
                    roleRespository.save(role1);
                    roleRespository.save(role2);
                    roleRespository.save(role3);
                    restaurantRepository.save(rest1);
                    restaurantRepository.save(rest2);
                    restaurantRepository.save(rest3);

        };
    }

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
//    CommandLineRunner initReview(ReviewRepository reviewRepository) {
//        return args -> {
//            Review review1 = new Review("Comments 1");
//            Review review2 = new Review ("Comments 2");
//            reviewRepository.save(review1);
//            reviewRepository.save(review2);
//
//        };

//    @Bean
//    CommandLineRunner initCuisine(CuisineRepository cuisineRepository) {
//        return args -> {
//            Cuisine cuisine1 = new Cuisine("Chinese");
//            Cuisine cuisine2 = new Cuisine ("American");
//            cuisineRepository.save(cuisine1);
//            cuisineRepository.save(cuisine2);
//
//        };
//    }

//        @Bean
//    CommandLineRunner initRole(RoleRespository roleRespository) {
//        return args -> {
//            Role role1 = new Role("Admin");
//            Role role2 = new Role ("User");
//            Role role3 = new Role ("Support");
//            roleRespository.save(role1);
//            roleRespository.save(role2);
//            roleRespository.save(role3);
//
//        };
//    }
//
//            @Bean
//    CommandLineRunner initRole(RestaurantRepository restaurantRepository) {
//        return args -> {
//            Restaurant rest1 = new Restaurant("Blaze pizza","10","3");
//            Restaurant rest2 = new Restaurant ("bovid","123","rating 0");
//            Restaurant rest3 = new Restaurant ("taste","12332","12");
//            restaurantRepository.save(rest1);
//            restaurantRepository.save(rest2);
//            restaurantRepository.save(rest3);
//
//        };
//    }

}


