package com.xuwei.music.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 歌单
 * @author 
 */
@Data
public class SongList implements Serializable {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 歌单图片
     */
    private String pic;

    /**
     * 歌单简介
     */
    private String songlist_intro;

    /**
     * 歌单风格
     */
    private String style;

    /**
     * 歌单制作者
     */
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSonglist_intro() {
        return songlist_intro;
    }

    public void setSonglist_intro(String songlist_intro) {
        this.songlist_intro = songlist_intro;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}