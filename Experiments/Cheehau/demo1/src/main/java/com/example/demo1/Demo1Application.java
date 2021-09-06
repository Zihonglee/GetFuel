package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class Demo1Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);

	}
@GetMapping
	public String demo1(){

		return "Hello World, The date is "+ LocalDate.now();
	}

//	public List<String> demo2(){
//		return List.of("hello","world");
//	}

//	public LocalDate demo1(){
//		return LocalDate.now();
//}

}
