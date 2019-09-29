package com.study.annotation.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author long
 * @Date 2019/9/29 17:02
 */
@Service
@Profile("Java7")
public class IterationCalculatingService implements CalculatingService {

    @Override
    public Integer sum(Integer... values) {
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        System.out.printf("[java7 迭代实现]%s 累加结果: %d\n", Arrays.asList(values), sum);
        return sum;
    }
}
