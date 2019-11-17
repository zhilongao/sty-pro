package com.common.exception;

/**
 * @Author long
 * @Date 2019/11/17 15:10
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BizException(String msg) {
        super(msg);
    }
}
