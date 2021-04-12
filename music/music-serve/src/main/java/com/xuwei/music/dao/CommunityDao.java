package com.xuwei.music.dao;

import com.xuwei.music.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommunityDao{
    /**
     * 根据id删除动态
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 添加
     *
     * @param record
     * @return
     */
    int insert(Community record);

    int insertSelective(Community record);

    /**
     * 上传url
     * @param record
     * @return
     */
    int uploadUrl(Community record);
 /**
     * 上传图片
     * @param record
     * @return
     */
    int uploadImg(Community record);

    /**
     * 查询所有动态
     *
     * @return
     */
    List<Community> allCommunity();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Community selectById(Integer id);

    /**
     * 根据时间查询
     * @param time
     * @return
     */
    List<Community> selectByTime(Date time);

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    List<Community> selectByUsername(String name);

    /**
     * 编辑动态
     * @param record
     * @return
     */
    int updateByIdSelective(Community record);

    int updateById(Community record);
}