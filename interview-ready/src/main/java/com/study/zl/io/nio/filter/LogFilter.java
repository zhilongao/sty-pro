package com.study.zl.io.nio.filter;

import java.nio.channels.SelectionKey;

/**
 * @Author long
 * @Date 2019/8/24 20:46
 */
public class LogFilter implements HandleFilter {
    @Override
    public boolean beforeHandle(SelectionKey key) {
        System.out.println("beforeHandle key: " + key);
        return false;
    }

    @Override
    public boolean afterHandle(SelectionKey key) {
        System.out.println("afterHandle key: " + key);
        return false;
    }
}
