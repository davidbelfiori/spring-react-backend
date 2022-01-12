package com.example.spring_react.repo;

import com.example.spring_react.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM  Student s where s.email= ?1" )
    Optional<Student> findStudentByEmail (String email);
}
