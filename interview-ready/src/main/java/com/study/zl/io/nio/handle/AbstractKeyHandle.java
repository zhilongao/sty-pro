package com.study.zl.io.nio.handle;

import com.study.zl.io.nio.filter.HandleFilter;
import com.study.zl.io.nio.filter.LogFilter;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author long
 * @Date 2019/8/24 20:41
 */
public abstract class AbstractKeyHandle implements SelectionKeyHandle{

    private static  Set<HandleFilter> filters = new HashSet<>();

    static {
        filters.add(new LogFilter());
    }


    @Override
    public void handle(SelectionKey key) {
        for (HandleFilter filter : filters) {
            filter.beforeHandle(key);
        }
        try {
            doHandle(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (HandleFilter filter : filters) {
            filter.afterHandle(key);
        }
    }

    protected void doHandle(SelectionKey key) throws IOException {

    }
}
