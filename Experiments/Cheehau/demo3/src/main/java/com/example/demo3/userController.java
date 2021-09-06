package com.example.demo3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "userData")

public class userController {
	@GetMapping
	public List<userData> demo3() {
		return List.of(new userData(
				1L,
				"GSOS",
				"GSOS@gmail.com",
				"GSOSISME",
				LocalDate.now()
				)
		);
	}
}
