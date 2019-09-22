package com.study.zl.io.nio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author long
 * @Date 2019/8/24 11:02
 * http 请求实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfRequest {
    private String methodName;
    private String protocol;
    private String version;
    private String acceptLanguage = "application/json";
    private String contentType = "application/json";

    @Override
    public String toString() {
        return this.methodName + "-" + this.protocol + "-" + this.version + "-" + this.acceptLanguage + "-" + this.contentType;
    }
}
