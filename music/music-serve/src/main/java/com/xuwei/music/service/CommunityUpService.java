package com.xuwei.music.service;

import com.xuwei.music.entity.CommunityUp;

import java.util.List;

/**
 * 社区点赞service接口
 */
public interface CommunityUpService {
    /**
     * 删除动态点赞
     * @param id
     * @return
     */
    boolean deleteCommunity(Integer id);

    /**
     * 删除动态的评论点赞
     * @param id
     * @param community_id
     * @return
     */
    boolean deleteComment(Integer id,Integer community_id);

    /**
     * 点赞
     * @param record
     * @return
     */
    boolean insert(CommunityUp record);


    /**
     * 检测用户是否点赞动态
     *
     * @param record
     * @return
     */
    CommunityUp hasCommunityUp(CommunityUp record);

    /**
     * 检测用户是否点赞动态评论
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
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedOfCommunity(Integer consumer_id);

    /**
     * 查询动态评论是否已点赞
     *
     * @param community_id
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedOfComment(Integer community_id, Integer consumer_id);

}
