package com.xuwei.music.service.impl;

import com.xuwei.music.dao.CommunityDao;
import com.xuwei.music.entity.Community;
import com.xuwei.music.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 社区service实现类
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityDao communityDao;
    /**
     * 根据id删除动态
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return communityDao.deleteById(id)>0?true:false;
    }

    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Community record) {
        return communityDao.insert(record)>0?true:false;
    }

    /**
     * 上传url
     *
     * @param record
     * @return
     */
    @Override
    public boolean uploadUrl(Community record) {
        return communityDao.uploadUrl(record)>0?true:false;
    }

    /**
     * 上传图片
     *
     * @param record
     * @return
     */
    @Override
    public boolean uploadImg(Community record) {
        return communityDao.uploadImg(record)>0?true:false;
    }

    /**
     * 查询所有动态
     *
     * @return
     */
    @Override
    public List<Community> allCommunity() {
        return communityDao.allCommunity();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Community selectById(Integer id) {
        return communityDao.selectById(id);
    }

    /**
     * 根据时间查询
     *
     * @param time
     * @return
     */
    @Override
    public List<Community> selectByTime(Date time) {
        return communityDao.selectByTime(time);
    }

    /**
     * 根据用户id查询
     *
     * @param name
     * @return
     */
    @Override
    public List<Community> selectByUsername(String name) {
        return communityDao.selectByUsername(name);
    }

    /**
     * 编辑动态
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateById(Community record) {
        return communityDao.updateById(record)>0?true:false;
    }
}
