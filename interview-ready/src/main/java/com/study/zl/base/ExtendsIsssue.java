package com.study.zl.base;

/**
 * @Author long
 * @Date 2019/8/6 9:46
 */
public class ExtendsIsssue {
    public static void main(String[] args) {
        boolean boolean1 = AA.class.isAssignableFrom(BB.class);
        boolean boolean2 = BB.class.isAssignableFrom(AA.class);
        System.out.println(boolean1);
        System.out.println(boolean2);

        AA a = new AA();
        BB b = new BB();
        boolean boolean3 = a instanceof BB;
        boolean boolean4 = b instanceof AA;
        System.out.println(boolean3);
        System.out.println(boolean4);

        // isAssignableFrom 是可以转换的从 判断的是 父类类型->子类类型
        // instanceof       属于某个类的实例        子类实例->父类类型

    }
}


class AA {

}

class BB extends AA{

}