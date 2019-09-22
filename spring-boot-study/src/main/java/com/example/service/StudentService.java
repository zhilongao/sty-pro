package com.example.service;

import com.example.entity.Student;

import java.util.List;

/**
 * @Author long
 * @Date 2019/8/19 18:53
 */
public interface StudentService {
    /**
     * 查询所有
     * @return
     */
    List<Student> selectAll();

}
