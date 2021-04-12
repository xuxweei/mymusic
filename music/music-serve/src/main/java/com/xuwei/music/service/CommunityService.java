package com.xuwei.music.service;

import com.xuwei.music.entity.Community;

import java.util.Date;
import java.util.List;

/**
 * 社区service
 */
public interface CommunityService {
    /**
     * 根据id删除动态
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 添加
     *
     * @param record
     * @return
     */
    boolean insert(Community record);

    /**
     * 上传url
     *
     * @param record
     * @return
     */
    boolean uploadUrl(Community record);

    /**
     * 上传图片
     *
     * @param record
     * @return
     */
    boolean uploadImg(Community record);

    /**
     * 查询所有动态
     *
     * @return
     */
    List<Community> allCommunity();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Community selectById(Integer id);

    /**
     * 根据时间查询
     *
     * @param time
     * @return
     */
    List<Community> selectByTime(Date time);

    /**
     * 根据用户id查询
     *
     * @param name
     * @return
     */
    List<Community> selectByUsername(String name);

    /**
     * 编辑动态
     *
     * @param record
     * @return
     */
    boolean updateById(Community record);
}
