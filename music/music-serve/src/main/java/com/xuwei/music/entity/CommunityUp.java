package com.xuwei.music.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * community_up
 * @author 
 */
@Table(name="community_up")
@ApiModel(value="com.xuwei.music.entity.CommunityUp")
@Data
public class CommunityUp implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Integer consumer_id;

    /**
     * 动态id
     */
    @ApiModelProperty(value="动态id")
    private Integer community_id;

    /**
     * 点赞的状态（0取消赞1已赞）
     */
    @ApiModelProperty(value="点赞的状态（0取消赞1已赞）")
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

    public Integer getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Integer community_id) {
        this.community_id = community_id;
    }

    public Integer getLike_status() {
        return like_status;
    }

    public void setLike_status(Integer like_status) {
        this.like_status = like_status;
    }
}