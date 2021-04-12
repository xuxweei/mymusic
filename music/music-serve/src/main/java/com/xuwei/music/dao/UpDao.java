package com.xuwei.music.dao;

import com.xuwei.music.entity.Up;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpDao {
    /**
     * 取消赞
     *
     * @param id
     * @return
     */
    int deleteUp(Integer id);

    /**
     * 点赞
     *
     * @param record
     * @return
     */
    int insert(Up record);

    int insertSelective(Up record);

    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    Up checkedUp(Up record);

    int updateByIdSelective(Up record);

    int updateById(Up record);

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
     * @param consumer_id
     * @return
     */
    List<Up> getLikedByCommentId(Integer comment_id,Integer consumer_id);

    /**
     * 取消赞
     *
     * @param record
     * @return
     */
    int update(Up record);
}