package com.study.zl.t4;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/10 10:20
 */
public class ConditionTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("", "");
        map.get("");


        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new ConditionWait(lock, condition)).start();
        new Thread(new ConditionNotify(lock, condition)).start();
    }
}
