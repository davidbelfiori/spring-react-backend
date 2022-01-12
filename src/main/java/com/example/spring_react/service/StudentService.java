package com.example.spring_react.service;

import com.example.spring_react.model.Student;
import com.example.spring_react.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {



    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public void deleteStudent(Integer id) {
        boolean exists=studentRepo.existsById(id);
        if (!exists){throw new IllegalStateException("student with id"+id+"does not exists");}
        studentRepo.deleteById(id);
    }


    public Student saveStudent(Student student) {
        Optional<Student> studentOptional= studentRepo.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email is already taken");
        }

        if (student.getAge()<18){throw new IllegalStateException("you must have 18 year or more");}
        return studentRepo.save(student);
    }

    public List<Student> findAllStudent(){
        return studentRepo.findAll();
    }


    public void deleteAllStudent(){
        studentRepo.deleteAll();
    }

    @Transactional
    public void updateStudent(Integer id, String name, String email, String address) {
        Student student= studentRepo.findById(id).orElseThrow(()-> new IllegalStateException("student with id"+id+"does not exists"));
        if(name != null && name.length() >0 && !Objects.equals(student.getName(),name)){ student.setName(name);}
        if(address != null && address.length() >0 && !Objects.equals(student.getAddress(),address)){ student.setAddress(address);}
        if(email != null && email.length() >0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional=studentRepo.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already taken ");
            }
            student.setEmail(email);
        }}
    }


