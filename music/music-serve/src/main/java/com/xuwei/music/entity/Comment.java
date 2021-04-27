package com.xuwei.music.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 评论类型（0歌曲1歌单）
     */
    private Integer type;

    /**
     * 评论歌曲id
     */
    private Integer song_id;

    /**
     * 评论歌单id
     */
    private Integer songlist_id;

    /**
     * 评论动态id
     */
    private Integer community_id;

    /**
     * 评论时间
     */
    private Date create_time;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer up;

    private Integer like_status;

    public Integer getLike_status() {
        return like_status;
    }

    public void setLike_status(Integer like_status) {
        this.like_status = like_status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public Integer getSonglist_id() {
        return songlist_id;
    }

    public void setSonglist_id(Integer songlist_id) {
        this.songlist_id = songlist_id;
    }

    public Integer getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Integer community_id) {
        this.community_id = community_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }
}