package com.pageable.springpageable.controller;

import com.pageable.springpageable.entity.Student;
import com.pageable.springpageable.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/test")
    public Page<Student> fetchByPage(Pageable pageable){
        return studentRepository.findAll(pageable);
    }
}
