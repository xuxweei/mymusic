package com.xuwei.music.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * community
 *
 * @author
 */
@Data
public class Community implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 上传的用户名字
     */
    private String name;

    /**
     * 类型(0歌曲1视频2图片)
     */
    private Integer type;

    /**
     * 上传的歌曲地址
     */
    private String url;

    /**
     * 介绍
     */
    private String content;

    /**
     * 发送时间
     */
    private Date create_time;

    /**
     * 点赞数
     */
    private Integer up;
    /**
     * 图片
     */
    private String img;

    private String userpic;
    private String songpic;
    private String lyric;
    private Integer upid;

    public Integer getUpid() {
        return upid;
    }

    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getSongpic() {
        return songpic;
    }

    public void setSongpic(String songpic) {
        this.songpic = songpic;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}