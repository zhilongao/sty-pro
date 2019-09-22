package com.study.zl.pattern.observer.jdk;

import lombok.Data;

import java.util.Observable;

/**
 * @Author long
 * @Date 2019/9/13 9:14
 * 科技频道
 */
@Data
public class TechnologyChannel extends Observable {

    private String title;

    public synchronized void setChanged() {
        super.setChanged();
    }

    public synchronized void clearChanged() {
        super.clearChanged();
    }
}
