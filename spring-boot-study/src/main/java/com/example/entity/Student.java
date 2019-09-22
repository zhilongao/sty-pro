package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author long
 * @Date 2019/8/19 18:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int id;
    private String sid;
    private String sname;
    private int gid;

}
