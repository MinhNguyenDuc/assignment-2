package com.example.assignment2.controller;

import com.example.assignment2.entity.AptechClass;
import com.example.assignment2.entity.Student;
import com.example.assignment2.repository.AptechClassRepository;
import com.example.assignment2.repository.StudentRepository;
import com.example.assignment2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private StudentService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AptechClassRepository aptechClassRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin() {
        return "redirect:/detail";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        String oldPass = student.getPassword();
        student.setPassword(passwordEncoder.encode(oldPass));
        service.store(student);
        return "redirect:/login";
    }

    @GetMapping(value = "/detail")
    public String details(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLoginUserEmail = authentication.getName();
        Student loggedInUser = studentRepository.findByEmail(currentLoginUserEmail).orElse(null);
        List<AptechClass> aptechClasses =aptechClassRepository.findAll();

        model.addAttribute("student", loggedInUser);
        model.addAttribute("aptechs", aptechClasses);
        return "detail";
    }
}
