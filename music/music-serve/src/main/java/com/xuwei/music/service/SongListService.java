package com.xuwei.music.service;

import com.xuwei.music.entity.SongList;

import java.util.List;

/**
 * 歌单Service
 */
public interface SongListService {

    /**
     * 添加
     *
     * @param record
     * @return
     */
    boolean insertSongList(SongList record);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteSongList(Integer id);


    /**
     * 修改
     *
     * @param record
     * @return
     */
    boolean updateSongList(SongList record);

    /**
     * 修改歌单图片
     *
     * @param record
     * @return
     */
    boolean updateSongListImgById(SongList record);

    /**
     * 根据主键查询整个对象
     *
     * @param id
     * @return
     */
    SongList selectById(Integer id);

    /**
     * 查询所有歌单
     *
     * @return
     */
    List<SongList> allSongList();

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     * @return
     */
    List<SongList> songListLikeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     * @return
     */
    List<SongList> songListLikeStyle(String style);

    /**
     * 根据标题精确查询
     *
     * @param title
     * @return
     */
    List<SongList> songListOfTitle(String title);
}
