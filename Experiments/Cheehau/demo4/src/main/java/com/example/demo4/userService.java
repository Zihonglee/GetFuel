package com.example.demo4;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class userService {
    public List<userData> demo4(){
        return List.of( new userData(
                1L,
                "GSOS",
                "GSOS@gmail.com",
                "GSOSISME",
                LocalDate.now()
        ));
    }
}
