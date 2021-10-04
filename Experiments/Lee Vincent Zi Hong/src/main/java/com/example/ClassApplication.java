package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.CuisineAPI.Cuisine;
import com.example.CuisineAPI.CuisineRepository;
import com.example.RestAPI.Restaurant;
import com.example.RestAPI.restaurantRepository;
import com.example.ReviewsAPI.Review;
import com.example.ReviewsAPI.ReviewRepository;
import com.example.RoleAPI.Role;
import com.example.RoleAPI.RoleRepository;
import com.example.demoAPI.Person;
import com.example.demoAPI.personRepository;

@SpringBootApplication
public class ClassApplication 
{	
	public static void main(String[] args) 
	{
		SpringApplication.run(ClassApplication.class, args);
	}
//	
//	@Bean
//	public CommandLineRunner initUser(personRepository userRepository, restaurantRepository restRepository, RoleRepository roleRepository, CuisineRepository cuisineRepository, ReviewRepository reviewRepository)
//	{
//		return args ->
//		{
//            Role role1 = new Role("Admin");
//            Role role2 = new Role ("User");
//            Role role3 = new Role ("Support");
//            
//			Person p1 = new Person("Vincent", "vincent@iastate.edu", "java");
//			Person p2 = new Person("Jayson", "jayson@iastate.edu", "java");
//			Person p3 = new Person("Cheehau", "cheehau@iastate.edu", "java");
//
//            Cuisine cuisine1 = new Cuisine("Chinese");
//            Cuisine cuisine2 = new Cuisine ("American");
//
//            Restaurant rest1 = new Restaurant("Blaze pizza", 10.00, 7.50, cuisine1);
//            Restaurant rest2 = new Restaurant ("bovid", 10.00, 8.5, cuisine1);
//            Restaurant rest3 = new Restaurant ("taste", 12.50 , 9.5, cuisine1);
//            
//            Review review1 = new Review("comments 1");
//            Review review2 = new Review("comments 2");
//            rest1.addReviews(review1);
//            
//            reviewRepository.save(review1);
//            reviewRepository.save(review2);
//            cuisineRepository.save(cuisine1);
//            cuisineRepository.save(cuisine2);
//            
//            roleRepository.save(role1);
//            roleRepository.save(role2);
//            roleRepository.save(role3);
//            restRepository.save(rest1);
//            restRepository.save(rest2);
//            restRepository.save(rest3);
//            
//            userRepository.save(p1);
//            userRepository.save(p2);
//            userRepository.save(p1);
//            userRepository.save(p3);
//		};
//	}
}
