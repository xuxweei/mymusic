package com.xuwei.music.dao;

import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.entity.Up;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityUpDao {
    int deleteById(Integer id);

    int insert(CommunityUp record);

    int insertSelective(CommunityUp record);

    /**
     * 检测用户是否点赞动态
     *
     * @param record
     * @return
     */
    CommunityUp hasCommunityUp(CommunityUp record);

    /**
     * 检测用户是否点赞动态
     *
     * @param record
     * @return
     */
    CommunityUp hasCommentUp(CommunityUp record);

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikes(Integer consumer_id);

    /**
     * 查询动态是否已点赞
     *
     * @param community_id
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedByCommunityId(Integer community_id, Integer consumer_id);

    /**
     * 查询动态评论是否已点赞
     *
     * @param comment_son_id
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedByCommentSonId(Integer comment_son_id, Integer consumer_id);

    /**
     * 取消动态赞
     *
     * @param record
     * @return
     */
    int updateCommunity(CommunityUp record);

    /**
     * 取消动态评论赞
     *
     * @param record
     * @return
     */
    int updateComment(CommunityUp record);

    int updateByPrimaryKeySelective(CommunityUp record);

    int updateByPrimaryKey(CommunityUp record);
}