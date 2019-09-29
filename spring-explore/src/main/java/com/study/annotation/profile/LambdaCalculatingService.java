package com.study.annotation.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author long
 * @Date 2019/9/29 16:58
 */
@Service
@Profile("Java8")
public class LambdaCalculatingService implements CalculatingService {

    @Override
    public Integer sum(Integer... values) {
        Integer sum = Stream.of(values).reduce(0, Integer::sum);
        System.out.printf("[java8 Lambda实现] %s 累加结果: %d\n", Arrays.asList(values), sum);
        return sum;
    }
}
