package com.xuwei.music.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * up
 * @author 
 */
@Data
public class Up implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer consumer_id;

    /**
     * 评论id
     */
    private Integer comment_id;

    /**
     * 点赞的状态（0取消赞1已赞）
     */
    private Integer like_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Integer consumer_id) {
        this.consumer_id = consumer_id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getLike_status() {
        return like_status;
    }

    public void setLike_status(Integer like_status) {
        this.like_status = like_status;
    }
}