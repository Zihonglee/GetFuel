package com.example;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner initUser(personRepository userRepository)
	{
		return args ->
		{
			Person p1 = new Person(UUID.randomUUID(), "Vincent", "vincent");
			Person p2 = new Person(UUID.randomUUID(), "Jayson", "jayson");
			Person p3 = new Person(UUID.randomUUID(), "Cheehau", "cheehau");
			userRepository.save(p1);
			userRepository.save(p2);
			userRepository.save(p3);
			
		};
	}
}
