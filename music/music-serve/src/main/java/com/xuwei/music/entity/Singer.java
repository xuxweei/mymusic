package com.xuwei.music.entity;

import java.io.Serializable;
import java.util.Date;

/*
 * 歌手
 * */
//Serializable：实现序列化
public class Singer implements Serializable {
    private int id;
    //账号
    private String name;
    //性别
    private int sex;
    //歌手图片
    private String pic;
    //歌手生日
    private Date singer_birth;
    //籍贯
    private String singer_location;
    //介绍信息
    private String singer_intro;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getSinger_birth() {
        return singer_birth;
    }

    public void setSinger_birth(Date singer_birth) {
        this.singer_birth = singer_birth;
    }

    public String getSinger_location() {
        return singer_location;
    }

    public void setSinger_location(String singer_location) {
        this.singer_location = singer_location;
    }

    public String getSinger_intro() {
        return singer_intro;
    }

    public void setSinger_intro(String singer_intro) {
        this.singer_intro = singer_intro;
    }

}
