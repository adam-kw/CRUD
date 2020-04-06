package com.adamkwiecinski.thymeleafdemo.thymeleafdemo.controller;

import com.adamkwiecinski.thymeleafdemo.thymeleafdemo.data.StudentRepository;
import com.adamkwiecinski.thymeleafdemo.thymeleafdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public String listStudents(Model model){

        List<Student> theStudents = new ArrayList<>();
        studentRepository.findAll().forEach(i -> theStudents.add(i));
        model.addAttribute("students", theStudents);

        return "list-students";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model model){

        Student tmpStudent = new Student();
        model.addAttribute("student", tmpStudent);

        return "add-form";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("studentId") int theId, Model model){

        Optional<Student> tmpStudent = studentRepository.findById(theId);
        model.addAttribute("student", tmpStudent);

        return "add-form";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") int theId){

        studentRepository.deleteById(theId);

        return "redirect:/students/list";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, Errors errors){

        if(errors.hasErrors()){
            return "add-form";
        }

        studentRepository.save(student);

        return "redirect:/students/list";
    }




}
