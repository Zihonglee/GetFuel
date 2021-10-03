package onetomany;


import onetomany.Cuisine.Cuisine;
import onetomany.Cuisine.CuisineRepository;
import onetomany.Restaurants.Restaurant;
import onetomany.Restaurants.RestaurantRepository;
import onetomany.Reviews.Review;
import onetomany.Reviews.ReviewRepository;
import onetomany.Roles.Role;
import onetomany.Roles.RoleRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import onetomany.Users.User;
import onetomany.Users.UserRepository;

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines and phones
    @Bean
    CommandLineRunner initUser(ReviewRepository reviewRepository, UserRepository userRepository, CuisineRepository cuisineRepository, RoleRespository roleRespository, RestaurantRepository restaurantRepository ) {
        return args -> {

            Role role1 = new Role("Admin");
            Role role2 = new Role ("User");
            Role role3 = new Role ("Support");


            User user1 = new User("John", "john@somemail.com","asd",role1);
            User user2 = new User("Jane", "jane@somemail.com","asdasd",role1);
            User user3 = new User("Justin", "justin@somemail.com","asdasdas",role1);

            Cuisine cuisine1 = new Cuisine("Chinese");
            Cuisine cuisine2 = new Cuisine ("American");

            Restaurant rest1 = new Restaurant("Blaze pizza","10","3",cuisine1);
            Restaurant rest2 = new Restaurant ("bovid","123","rating 0",cuisine1);
            Restaurant rest3 = new Restaurant ("taste","12332","12",cuisine1);
            Review review1 = new Review("comments 1");
            Review review2 = new Review("comments 2");
            rest1.addReviews(review1);
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
            userRepository.save(user1);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        };
    }

//        @Bean
//    CommandLineRunner initUser(ReviewRepository reviewRepository, UserRepository userRepository, CuisineRepository cuisineRepository, RoleRespository roleRespository, RestaurantRepository restaurantRepository ) {
//            return args -> {
//
//                Role role = new Role ("admin");
//                User user1 = new User("Cheehau","cheehau@iastate.edu", "asd",role);
//                User user2 = new User("user2","user2@iastate.edu", "asd",role);
//
//                roleRespository.save(role);
//                userRepository.save(user1);
//                userRepository.save(user2);
//                Cuisine cuisine1 = new Cuisine("Chinese");
//                Cuisine cuisine2 = new Cuisine("water");
//                Restaurant rest1 = new Restaurant("rest1","10","3",cuisine2);
//                Restaurant rest2 = new Restaurant("rest2","10","3",cuisine2);
//
//                cuisine2.addRestaurant(rest1);
//                cuisine2.addRestaurant(rest2);
//
//
//                cuisineRepository.save(cuisine1);
//                cuisineRepository.save(cuisine2);
//
//                restaurantRepository.save(rest1);
//                restaurantRepository.save(rest2);
//
//
//            };
//        }

}