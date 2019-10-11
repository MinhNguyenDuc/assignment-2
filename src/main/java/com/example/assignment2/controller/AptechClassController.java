package com.example.assignment2.controller;

import com.example.assignment2.entity.AptechClass;
import com.example.assignment2.entity.Student;
import com.example.assignment2.repository.AptechClassRepository;
import com.example.assignment2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/class")
public class AptechClassController {
    @Autowired
    AptechClassRepository aptechClassRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public String list(Model model){
        List<AptechClass> aptechClasses = aptechClassRepository.findAll();
        model.addAttribute("classes", aptechClasses);
        return "list-class";
    }

    @GetMapping(value = "/{id}")
    public String list(@PathVariable int id, Model model){
        AptechClass aptechClass = aptechClassRepository.findById(id).orElse(null);
        List<Student> students = studentRepository.findAll();
        if(aptechClass == null){
            return "redirect:/class";
        }
        model.addAttribute("class", aptechClass);
        model.addAttribute("students", students);

        return "class-detail";
    }

    @PostMapping(value = "/{id}")
    public String update(@PathVariable int id, @RequestParam(value = "student") int[] student){
        AptechClass aptechClass = aptechClassRepository.findById(id).orElse(null);
        for (int sid: student
        ) {
            Student student1 = studentRepository.findById(sid).orElse(null);
            aptechClass.addStudent(student1);
        }
        aptechClassRepository.save(aptechClass);
        return "redirect:/class";
    }
}
