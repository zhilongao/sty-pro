package com.example.dao;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author long
 * @Date 2019/8/19 18:49
 */
@Mapper
public interface StudentMapper {

    /**
     * 查询所有
     * @return
     */
    List<Student> selectALl();

}
