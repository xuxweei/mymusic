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
    public boolean deleteUp(Integer id) {
        return communityUpDao.deleteUp(id)>0?true:false;
    }

    /**
     * 点赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(CommunityUp record) {
        return communityUpDao.insert(record)>0;
    }

    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    @Override
    public CommunityUp checkedUp(CommunityUp record) {
        return communityUpDao.checkedUp(record);
    }

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLiked(Integer consumer_id) {
        return communityUpDao.getLiked(consumer_id);
    }

    /**
     * 查询评论是否已点赞
     *
     * @param community_id
     * @param consumer_id
     * @return
     */
    @Override
    public List<CommunityUp> getLikedByCommunityId(Integer community_id, Integer consumer_id) {
        return communityUpDao.getLikedByCommunityId(community_id, consumer_id);
    }

    /**
     * 取消赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean update(CommunityUp record) {
        return communityUpDao.update(record)>0;
    }
}
