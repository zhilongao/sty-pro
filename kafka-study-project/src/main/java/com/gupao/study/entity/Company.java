package com.gupao.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author long
 * @Date 2019/8/15 10:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    private String name;
    private String address;
}
