package com.study.zl.io.nio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author long
 * @Date 2019/8/24 11:03
 * http响应实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfResponse {
    private String acceptLanguage = "application/json";
    private String contentType = "application/json";

    @Override
    public String toString() {
        return this.acceptLanguage + "-" + this.contentType;
    }
}
