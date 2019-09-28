package com.study.annotation.parser.bean;

import com.study.annotation.parser.TransactionalService;

/**
 * @Author long
 * @Date 2019/9/28 16:07
 * 关于注解的隐式覆盖和显示覆盖
 */
@TransactionalService(value = "transactionalServiceBean")
public class TransactionalServiceBean {

    public void save() {
        System.out.println("保存操作!");
    }
}
