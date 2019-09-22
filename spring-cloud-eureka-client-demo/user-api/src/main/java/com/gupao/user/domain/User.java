package com.gupao.user.domain;

/**
 * @Author long
 * @Date 2019/7/28 10:17
 * @Description 用户模型API
 */
public class User {
    private Long id;
    private String name;

    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "user:[id:" + this.getId() + ",name:" + this.getName() +"]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
