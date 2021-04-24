package com.xuwei.music.dao;

import com.xuwei.music.entity.CommunityUp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xuwei.music.entity.Up;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityUpDao {

    /**
     * 取消赞
     *
     * @param id
     * @return
     */
    int deleteUp(Integer id);


    int deleteByPrimaryKey(Integer id);
    /**
     * 点赞
     *
     * @param record
     * @return
     */
    int insert(CommunityUp record);

    int insertSelective(CommunityUp record);
    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    CommunityUp checkedUp(CommunityUp record);

    CommunityUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityUp record);

    int updateByPrimaryKey(CommunityUp record);
    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLiked(Integer consumer_id);

    /**
     * 查询评论是否已点赞
     * @param community_id
     * @param consumer_id
     * @return
     */
    List<CommunityUp> getLikedByCommunityId(Integer community_id,Integer consumer_id);

    /**
     * 取消赞
     *
     * @param record
     * @return
     */
    int update(CommunityUp record);
}