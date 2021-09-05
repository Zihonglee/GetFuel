package com.example.springboot;

//service layer manage business  logic

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class studentService {
    public List<studentList> getStudent() {
        return List.of(
                new studentList(
                        1L,
                        LocalDateTime.now(),
                        "GSOS",
                        "GSOS@GOD.com",
                        "1234"
                )
        );
    }
}
