package com.study.zl.pattern.delegate.boss;

import java.util.*;

/**
 * @Author long
 * @Date 2019/3/6 22:23
 */
public class Leader{

    private Map<String, Employee> map;

    public Leader(){
        map = new HashMap<String, Employee>();
        map.put("login",new EmployeeA());
        map.put("thread",new EmployeeB());
    }

    public void doWork(String command){
        // 此处需要判断
        map.get(command).writeCode(command);
    }
}
