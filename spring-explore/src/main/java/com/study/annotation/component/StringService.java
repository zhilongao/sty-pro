package com.study.annotation.component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author long
 * @Date 2019/9/21 16:21
 */
@StringRepository(value = "stringService")
public class StringService {

    public List<String> findAll(){
        return Arrays.asList("jack", "tom", "seven");
    }
}
