package com.xuwei.music.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * rank
 * @author 
 */
@Data
public class Rank implements Serializable {
    private Integer id;

    /**
     * 歌单id
     */
    private Integer songlist_id;

    /**
     * 用户id
     */
    private Integer consumer_id;

    /**
     * 评分
     */
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSonglist_id() {
        return songlist_id;
    }

    public void setSonglist_id(Integer songlist_id) {
        this.songlist_id = songlist_id;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}