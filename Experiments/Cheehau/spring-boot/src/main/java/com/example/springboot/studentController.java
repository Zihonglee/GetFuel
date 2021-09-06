package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//api level

@RestController
@RequestMapping(path="api/studentList")
public class studentController {

    private final studentService studentService;

@Autowired
    public studentController(studentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<studentList> getStudent() {
return studentService.getStudent();
    }

}
