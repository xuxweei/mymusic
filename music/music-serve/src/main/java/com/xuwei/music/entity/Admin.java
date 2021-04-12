package com.xuwei.music.entity;

import java.io.Serializable;

/*
* 管理员
* */
//Serializable：实现序列化
public class Admin implements Serializable {
    private int id;
    //账号
    private String name;
    //密码
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
