package com.study.zl.io.nio.filter;

import java.nio.channels.SelectionKey;

/**
 * @Author long
 * @Date 2019/8/24 20:39
 */
public interface HandleFilter {
    /**
     * @param key
     * @return
     *
     */
    boolean beforeHandle(SelectionKey key);

    /**
     * @param key
     * @return
     */
    boolean afterHandle(SelectionKey key);
}
