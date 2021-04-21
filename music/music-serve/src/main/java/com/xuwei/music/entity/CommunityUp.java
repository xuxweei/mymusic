package com.xuwei.music.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
    private Integer consumerId;

    /**
     * 动态id
     */
    @ApiModelProperty(value="动态id")
    private Integer communityId;

    /**
     * 社区评论id
     */
    @ApiModelProperty(value="社区评论id")
    private Integer commentSonId;

    /**
     * 点赞的状态（0取消赞1已赞）
     */
    @ApiModelProperty(value="点赞的状态（0取消赞1已赞）")
    private Integer likeStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getCommentSonId() {
        return commentSonId;
    }

    public void setCommentSonId(Integer commentSonId) {
        this.commentSonId = commentSonId;
    }

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }
}