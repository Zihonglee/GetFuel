package onetoone;

import onetoone.Cuisine.Cuisine;
import onetoone.Cuisine.CuisineRepository;
import onetoone.Restaurants.Restaurant;
import onetoone.Restaurants.RestaurantRepository;
import onetoone.Reviews.Review;
import onetoone.Reviews.ReviewRepository;
import onetoone.Roles.Role;
import onetoone.Roles.RoleRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import onetoone.Users.User;
import onetoone.Users.UserRepository;


@SpringBootApplication
@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines

    @Bean
    CommandLineRunner initUser(UserRepository userRepository, RoleRespository roleRespository, CuisineRepository cuisineRepository, RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        return args -> {
            Role admin = new Role("Admin");
            Role support = new Role("Support");
            Role normalUser = new Role("User");
            User user1 = new User("John", "john@somemail.com","password1",admin);
            User user2 = new User("Jane", "jane@somemail.com","password2",support);
            User user3 = new User("Justin", "justin@somemail.com","password3",normalUser);
            roleRespository.save(admin);
            roleRespository.save(support);
            roleRespository.save(normalUser);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            Cuisine chinese = new Cuisine("Chinese");
            Cuisine japanese = new Cuisine("Japanese");
            Cuisine thailandese = new Cuisine("thailandese");
            Restaurant restaurant1 = new Restaurant("Taste place","10","3",chinese);
            Restaurant restaurant2 = new Restaurant("Ichiban","10","4",japanese);
            Restaurant restaurant3 = new Restaurant("ThaiHouSek","4","5",thailandese);
            Review review1 = new Review("I love thaifood baby");
            Review review2 = new Review("I love malaysia");
            restaurant3.addReviews(review1);
            restaurant3.addReviews(review2);
            cuisineRepository.save(chinese);
            cuisineRepository.save(japanese);
            cuisineRepository.save(thailandese);
            reviewRepository.save(review1);
            reviewRepository.save(review2);
            restaurantRepository.save(restaurant1);
            restaurantRepository.save(restaurant2);
            restaurantRepository.save(restaurant3);


        };
    }

}
