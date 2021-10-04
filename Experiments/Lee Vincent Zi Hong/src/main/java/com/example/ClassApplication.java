package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.RestAPI.restaurantRepository;
import com.example.demoAPI.personRepository;
import com.example.demoMODEL.Person;

@SpringBootApplication
public class ClassApplication 
{	
	public static void main(String[] args) 
	{
		SpringApplication.run(ClassApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initUser(personRepository userRepository, restaurantRepository restRepository)
	{
		return args ->
		{
			Person p1 = new Person("Vincent", "vincent");
			Person p2 = new Person("Jayson", "jayson");
			Person p3 = new Person("Cheehau", "cheehau");
			userRepository.save(p1);
			userRepository.save(p2);
			userRepository.save(p3);
			
		};
	}
}
