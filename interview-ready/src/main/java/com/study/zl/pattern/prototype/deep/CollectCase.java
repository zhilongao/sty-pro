package com.study.zl.pattern.prototype.deep;

import java.io.*;

/**
 * @Author long
 * @Date 2019/3/3 10:31
 * 案件--演示深度拷贝
 */
public class CollectCase implements Cloneable,Serializable {

    private String caseId;
    private User user;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
