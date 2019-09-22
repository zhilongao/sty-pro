package com.gupao.ready;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * @Author long
 * @Date 2019/7/29 18:09
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        Random random = new Random();

        Single
                // 发布数据
                .just("hello,world")
                // 订阅的线程池 immediate = Thread.currentThread
                .subscribeOn(Schedulers.immediate())
                .subscribe(new Observer<String>() {
                    // 程序正常结束
                    @Override
                    public void onCompleted() {
                        System.out.println("-->执行结束");
                    }
                    // 异常流程(结束)
                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("-->熔断保护");
                    }
                    // s:消费数据=hello,world
                    @Override
                    public void onNext(String s) {
                        int value = random.nextInt(200);
                        System.out.println("hello,world cost " + value + "ms");
                        if (value > 100) {
                            throw new RuntimeException("Timeout!");
                        }
                    }
                });
    }

}
