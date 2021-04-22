package com.xuwei.music.service.impl;

import com.xuwei.music.dao.CommunityUpDao;
import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.service.CommunityUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityUpServiceImpl implements CommunityUpService {
    @Autowired
    private CommunityUpDao communityUpDao;

    /**
     * 删除动态点赞
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteCommunity(Integer id) {
        return communityUpDao.deleteCommunity(id)>0?true:false;
    }

    /**
     * 删除动态的评论点赞
     *
     * @param id
     * @param community_id
     * @return
     */
    @Override
    public boolean deleteComment(Integer id, Integer community_id) {
        return communityUpDao.deleteComment(id, community_id)>0?true:false;
    }

    /**
     * 点赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(CommunityUp record) {
        return communityUpDao.insert(record)>0?true:false;
    }

    /**
     * 检测用户是否点赞动态
     *
     * @param record
     * @return
     */
    @Override
    public CommunityUp hasCommunityUp(CommunityUp record) {
        return communityUpDao.hasCommunityUp(record);
    }

    /**
     * 检测用户是否点赞动态评论
     *
     * @param record
     * @return
     */
    @Override
    public CommunityUp hasCommentUp(CommunityUp record) {
        return communityUpDao.hasCommentUp(record);
    }

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikes(Integer consumer_id) {
        return communityUpDao.getLikes(consumer_id);
    }

    /**
     * 查询动态是否已点赞
     *
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikedOfCommunity(Integer consumer_id) {
        return communityUpDao.getLikedOfCommunity(consumer_id);
    }

    /**
     * 查询动态评论是否已点赞
     *
     * @param community_id
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikedOfComment(Integer community_id, Integer consumer_id) {
        return communityUpDao.getLikedOfComment(community_id,consumer_id);
    }
}
