package com.study.dao;

import com.study.entity.Gride;

import java.util.List;

public interface GrideMapper {
    int insert(Gride record);

    int insertSelective(Gride record);
    // 查询所有,不携带学生信息
    List<Gride> selectAllList(Gride record);
    // 查询所有,携带学生信息
    List<Gride> selectAllListWithStudents(Gride record);
}