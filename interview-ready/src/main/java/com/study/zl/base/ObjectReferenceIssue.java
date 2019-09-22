package com.study.zl.base;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/7/24 10:07
 * @Desciption 对象引用类型  强引用
 */
public class ObjectReferenceIssue {

    /**
     * 强引用对象
     */
    private static Object strongRef = new Object();

    public static void main(String[] args) {
        // strongReference();
        softReference();
        weakReference();
        phantomReference();
    }

    /**
     * 强引用,任何情况下都不会被回收
     */
    public static void strongReference() {
        Object obj = strongRef;
        strongRef = null;
        System.gc();
        System.out.println(obj);
    }

    /**
     * 软引用:回收时机,内存充足不回收, 内存不足或者reference指向为null时回收
     */
    public static void softReference() {
        Object softObj = new Object();
        SoftReference softReference = new SoftReference(softObj);
        softObj = null;
        System.gc();
        System.out.println(softReference.get());
    }

    /**
     * 弱引用:回收时机,对象指向为null时,执行system.gc就会被回收掉
     */
    public static void weakReference() {
        Object weakObj = new Object();
        WeakReference weakReference = new WeakReference(weakObj);
        weakObj = null;
        System.gc();
        System.out.println(weakReference.get());
    }

    /**
     * 需引用
     */
    public static void phantomReference() {
        System.out.println("******虚引用****");
        ReferenceQueue queue = new ReferenceQueue();
        Object phantomObj = new Object();
        PhantomReference phantomReference = new PhantomReference(phantomObj, queue);
        System.out.println(phantomReference.get());
        phantomObj = null;
        System.out.println(phantomReference.get());
        System.gc();
        System.out.println(phantomReference.get());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(queue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
