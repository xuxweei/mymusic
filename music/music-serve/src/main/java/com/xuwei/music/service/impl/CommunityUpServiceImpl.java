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
     * 取消赞
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return communityUpDao.deleteById(id)>0?true:false;
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
        return hasCommentUp(record);
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
     * @param community_id
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikedByCommunityId(Integer community_id, Integer consumer_id) {
        return communityUpDao.getLikedByCommunityId(community_id,consumer_id);
    }

    /**
     * 查询动态评论是否已点赞
     *
     * @param comment_son_id
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikedByCommentSonId(Integer comment_son_id, Integer consumer_id) {
        return communityUpDao.getLikedByCommentSonId(comment_son_id,consumer_id);
    }

    /**
     * 取消动态赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateCommunity(CommunityUp record) {
        return communityUpDao.updateCommunity(record)>0?true:false;
    }

    /**
     * 取消动态评论赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateComment(CommunityUp record) {
        return communityUpDao.updateComment(record)>0?true:false;
    }
}
