package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author long
 * @Date 2019/8/19 18:56
 */
@RestController
@RequestMapping("/student")
public class StudentServiceController {

    private final static AtomicInteger generateLong = new AtomicInteger(100);


    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAll(){
        return studentService.selectAll();
    }

    @PostMapping("/insert")
    public Student insertAll() {
        Student student = new Student();
        student.setSid("student_" + generateLong.getAndIncrement());
        student.setSname("学生_" + generateLong.getAndIncrement());
        student.setGid(generateLong.getAndIncrement());

        return null;
    }
}
