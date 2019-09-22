package com.study.zl.pattern.prototype.deep;


import com.study.zl.pattern.prototype.util.CloneUtils;

/**
 * @Author long
 * @Date 2019/3/10 17:04
 */
public class DeepCloneTest {

    public static void main(String[] args) {
        CollectCase case1 = new CollectCase();
        User  u1 = new User("p1","jack");
        case1.setCaseId("jxj0001");
        case1.setUser(u1);
        // 浅拷贝
        CollectCase case2 = null;
        try {
            case2 = (CollectCase)case1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // 深拷贝
        CollectCase case3 = (CollectCase)CloneUtils.deepCloneByStream(case1);

        System.out.println(case1 + "--" + case1.getCaseId() + "--" + case1.getUser());
        System.out.println(case2 + "--" + case1.getCaseId() + "--" + case2.getUser());
        System.out.println(case3 + "--" + case1.getCaseId() + "--" + case3.getUser());

    }

}
