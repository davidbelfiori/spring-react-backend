package com.example.spring_react.controller;


import com.example.spring_react.model.Student;
import com.example.spring_react.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> allStudent(Student student){
        return studentService.findAllStudent();
    }


    @PostMapping("/add")
    public String add(@RequestBody Student student){
        studentService.saveStudent(student);
        return "studente aggiunto \n"+student;
    }

    @DeleteMapping("/delete")
    public void delete(){
        studentService.deleteAllStudent();
    }

    @DeleteMapping(path= "/delete/{id}")
    public void deleteStudentById(@PathVariable ("id") Integer id){
      studentService.deleteStudent(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable ("id") Integer id,@RequestParam (required=false) String name ,@RequestParam (required=false) String address,@RequestParam (required=false) String email ){
      studentService.updateStudent(id,name,email,address);
    }
}
