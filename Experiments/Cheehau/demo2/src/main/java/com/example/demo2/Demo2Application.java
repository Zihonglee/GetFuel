package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class Demo2Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo2Application.class, args);
	}
@GetMapping
	public List<userData> demo2(){
		return List.of(
				new userData(
				1L,
				"GSOS",
				"GSOS@gmail.com",
				"GSOSISME",
				LocalDate.now()
		)
		);
	}

}
