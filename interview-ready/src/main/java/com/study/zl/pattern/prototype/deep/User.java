package com.study.zl.pattern.prototype.deep;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/3/3 10:32
 */
public class User implements Serializable {

    private String uid;
    private String uName;

    public User(String uid, String uName) {
        this.uid = uid;
        this.uName = uName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

}
