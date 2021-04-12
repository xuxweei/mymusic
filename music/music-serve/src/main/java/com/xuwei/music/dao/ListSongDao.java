package com.xuwei.music.dao;

import com.xuwei.music.entity.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单歌曲dao
 */
@Repository
public interface ListSongDao {
    /**
     * 根据id删除
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
    int insert(ListSong record);

    /**
     * 添加歌单歌曲
     *
     * @param record
     * @return
     */
    int insertListSong(ListSong record);

    /**
     * 根据id查询整个对象
     *
     * @param id
     * @return
     */
    ListSong selectById(Integer id);

    /**
     * 更新歌单
     *
     * @param record
     * @return
     */
    int updateListSong(ListSong record);

    int updateByPrimaryKey(ListSong record);

    /**
     * 查询所有歌单里面的歌曲
     *
     * @return
     */
    List<ListSong> allListSong();

    /**
     * 根据歌曲id查询歌单
     *
     * @param songListId
     * @return
     */
    List<ListSong> listSongOfSongListId(Integer songListId);

    /**
     * 根据song_id和songlist_id删除
     */
    int deleteBySongIdAndSongListId(Integer song_id, Integer songlist_id);
}