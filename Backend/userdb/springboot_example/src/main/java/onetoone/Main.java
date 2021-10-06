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
}
