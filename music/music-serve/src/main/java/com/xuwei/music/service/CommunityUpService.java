package com.xuwei.music.service;

import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.entity.Up;

import java.util.List;

public interface CommunityUpService {
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
    boolean insert(CommunityUp record);

    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    CommunityUp checkedUp(CommunityUp record);

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLiked(Integer consumer_id);

    /**
     * 查询评论是否已点赞
     *
     * @param community_id
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedByCommunityId(Integer community_id, Integer consumer_id);

    /**
     * 取消赞
     *
     * @param record
     * @return
     */
    boolean update(CommunityUp record);
}
