package com.study.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/9/3 11:34
 */
@Data
public class User implements Serializable {
    private String name;
    private int age;
    private String address;
}
