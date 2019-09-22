package com.study.zl.io.nio.handle;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @Author long
 * @Date 2019/8/24 11:09
 */
public interface SelectionKeyHandle {
    /**
     * 处理SelectionKey
     * @param key
     */
    void handle(SelectionKey key) throws IOException;
}
