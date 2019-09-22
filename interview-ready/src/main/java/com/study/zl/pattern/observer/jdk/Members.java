package com.study.zl.pattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author long
 * @Date 2019/9/13 9:13
 * 会员
 */
public class Members implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TechnologyChannel) {
            TechnologyChannel channel = (TechnologyChannel)o;
            String title = channel.getTitle();
            NotifyMessage message = (NotifyMessage)arg;
            System.out.println("channel:" + title);
            System.out.println("message:" + message);
            System.out.println("=======================");
        }
    }
}
