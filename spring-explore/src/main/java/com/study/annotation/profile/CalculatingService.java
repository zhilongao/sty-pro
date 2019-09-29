package com.study.annotation.profile;

/**
 * @Author long
 * @Date 2019/9/29 16:57
 */
public interface CalculatingService {

    /**
     * 累加求和
     * @param values
     * @return
     */
    Integer sum(Integer... values);
}
