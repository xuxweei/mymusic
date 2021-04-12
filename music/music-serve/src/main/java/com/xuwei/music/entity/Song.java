package com.xuwei.music.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌曲
 */
//Serializable：实现序列化
public class Song implements Serializable {
    //歌曲id
    private int id;
    //歌手id
    private int singer_id;
    //歌名
    private String song_name;
    //歌曲介绍
    private String song_intro;
    //歌曲创建时间
    private Date create_time;
    //歌曲更新时间
    private Date update_time;
    //歌曲图片
    private String song_pic;
    //歌词
    private String lyric;
    //歌曲地址
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_intro() {
        return song_intro;
    }

    public void setSong_intro(String song_intro) {
        this.song_intro = song_intro;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getSong_pic() {
        return song_pic;
    }

    public void setSong_pic(String song_pic) {
        this.song_pic = song_pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                "url='" + url + '\'' +
                '}';
    }
}
