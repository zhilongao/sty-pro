package com.study.zl.pattern.observer.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/9/13 9:28
 * 通知消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyMessage implements Serializable {

    private String id;
    private Long createTime;
    private String title;
    private String content;
}
