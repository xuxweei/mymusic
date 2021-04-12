package com.xuwei.music.service.impl;

import com.xuwei.music.dao.UpDao;
import com.xuwei.music.entity.Up;
import com.xuwei.music.service.UpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评价service实现
 */
@Service
public class UpServiceImpl implements UpService {
    @Autowired
    private UpDao upDao;
    /**
     * 取消赞
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUp(Integer id) {
        return upDao.deleteUp(id)>0?true:false;
    }

    /**
     * 点赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Up record) {
        return upDao.insert(record)>0?true:false;
    }

    /**
     * 检测用户是否点赞
     *
     * @param record
     * @return
     */
    @Override
    public Up checkedUp(Up record) {
        return upDao.checkedUp(record);
    }

    /**
     * 查询用户所有的点赞信息
     *
     * @param consumer_id
     * @return
     */
    @Override
    public List<Up> getLiked(Integer consumer_id) {
        return upDao.getLiked(consumer_id);
    }

    @Override
    public List<Up> getLikedByCommentId(Integer comment_id,Integer consumer_id) {
        return upDao.getLikedByCommentId(comment_id,consumer_id);
    }

    /**
     * 取消赞
     *
     * @param record
     * @return
     */
    @Override
    public boolean update(Up record) {
        return upDao.update(record)>0?true:false;
    }
}
