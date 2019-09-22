package com.example.service;

import com.example.dao.StudentMapper;
import com.example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author long
 * @Date 2019/8/19 18:54
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> selectAll() {
        return studentMapper.selectALl();
    }

}
