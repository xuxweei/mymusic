package com.xuwei.music.dao;

import com.xuwei.music.entity.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单dao
 */
@Repository
public interface SongListDao {


    /**
     * 添加
     *
     * @param record
     * @return
     */
    int insertSongList(SongList record);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteSongList(Integer id);


    /**
     * 修改
     *
     * @param record
     * @return
     */
    int updateSongList(SongList record);

    /**
     * 修改歌单图片
     *
     * @param record
     * @return
     */
    int updateSongListImgById(SongList record);

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