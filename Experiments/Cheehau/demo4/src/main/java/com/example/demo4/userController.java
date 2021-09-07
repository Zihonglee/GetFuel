package com.example.demo4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "userData")
public class userController    {

    private final userService userservice;

@Autowired
    public userController(userService userservice) {
        this.userservice = userservice;
    }
@GetMapping
    public List<userData> demo4(){
return userservice.demo4();
    }
}
