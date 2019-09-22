package com.study.zl.pattern.observer.jdk;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/9/13 9:16
 */
public class App {
    public static void main(String[] args) {
        // 科技频道
        TechnologyChannel technologyChannel = new TechnologyChannel();
        technologyChannel.setTitle("科技频道");
        // 用户
        Members member1 = new Members();
        Members member2 = new Members();
        // 用户订阅该科技频道
        technologyChannel.addObserver(member1);
        technologyChannel.addObserver(member2);
        // 给会员定时推送消息
        String[] messages = {"华为", "小米", "苹果"};
        Random random = new Random();
        for (int i = 0; i < 10 ; i++) {
            NotifyMessage message = new NotifyMessage();
            String id = UUID.randomUUID().toString();
            message.setId(id);
            message.setCreateTime(System.currentTimeMillis());
            message.setTitle("手机");
            message.setContent(messages[random.nextInt(3)] + "又出新机，赶快来订购吧!");
            technologyChannel.setChanged();
            technologyChannel.notifyObservers(message);
            technologyChannel.clearChanged();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
