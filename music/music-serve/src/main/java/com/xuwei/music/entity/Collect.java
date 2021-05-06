package com.xuwei.music.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * collect
 * @author 
 */
@Data
public class Collect implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 收藏类型（0歌曲1歌单）
     */
    private Integer type;

    /**
     * 歌曲id
     */
    private Integer song_id;


    /**
     * 收藏时间
     */
    private Date create_time;

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}