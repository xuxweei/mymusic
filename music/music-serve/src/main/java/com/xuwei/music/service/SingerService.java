package com.xuwei.music.service;

import com.xuwei.music.entity.Singer;

import java.util.List;

/*
 * 歌手service接口
 * */
public interface SingerService {
    /**
     * 增加
     */
    public boolean addSinger(Singer singer);

    /**
     * 修改
     */
    public boolean updateSinger(Singer singer);

    /**
     * 删除
     */
    public boolean deleteSinger(Integer id);

    /**
     * 根据id查询
     */
    public Singer selectSingerById(Integer id);

    /**
     * 查询所有歌手
     */
    public List<Singer> allSinger();

    /**
     * 根据歌手名字模糊查询
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Singer> singerOfSex(Integer sex);

    /**
     * 修改图片
     */
    public boolean updateSingerImg(Singer singer);
}
