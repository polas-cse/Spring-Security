package com.spring.security.controller;

import com.spring.security.entity.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Polas"),
            new Student(2,"Shopon"),
            new Student(3,"Naime")
    );

    //hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudent(){
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student registerNewStudent(@RequestBody Student student){
        return student;
    }

    @DeleteMapping(value = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public Integer deleteStudent(@PathVariable("studentId") Integer integer){
        return integer;
    }

    @PutMapping(value = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(@PathVariable("studentId") Integer integer, @RequestBody Student student){
        return student;
    }

}
