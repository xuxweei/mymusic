package com.xuwei.music.service;

import com.xuwei.music.entity.Up;

import java.util.List;

/**
 * 点赞service接口
 */
public interface UpService {
    /**
     * 取消赞
     *
     * @param id
     * @return
     */
    boolean deleteUp(Integer id);

    /**
     * 点赞
     *
     * @param record
     * @return
     */
    boolean insert(Up record);

    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    Up checkedUp(Up record);

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    List<Up> getLiked(Integer consumer_id);

    /**
     * 查询评论是否已点赞
     * @param comment_id
     * @return
     */
    List<Up> getLikedByCommentId(Integer comment_id,Integer consumer_id);

    /**
     * 取消赞
     * @param record
     * @return
     */
    boolean update(Up record);

}
