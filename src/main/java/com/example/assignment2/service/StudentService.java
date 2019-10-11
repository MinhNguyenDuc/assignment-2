package com.example.assignment2.service;

import com.example.assignment2.entity.Student;
import com.example.assignment2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;


    public Student store(Student student) {
        return repository.save(student);
    }

}
