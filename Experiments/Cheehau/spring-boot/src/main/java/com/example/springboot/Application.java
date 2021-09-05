package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

// to call SpringBoot


@SpringBootApplication

public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
