package com.xuwei.music.dao;

import com.xuwei.music.entity.Song;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲Dao
 */
@Repository
public interface SongDao {
    /**
     * 增加
     */
    public int addSong(Song song);

    /**
     * 修改
     */
    public int updateSong(Song song);

    /**
     * 删除
     */
    public int deleteSong(Integer id);

    /**
     * 根据id查询
     */
    public Song selectSongById(Integer id);

    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();

    /**
     * 根据歌曲名字模糊查询
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据专辑名字模糊查询
     */
    public List<Song> likeSongOfIntro(String intro);


    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer singerId);

    /**
     * 修改歌曲图片
     */
    public int updateSongImg(Song song);

    /**
     * 修改歌曲地址
     *
     * @param record
     * @return
     */
    public int updateSongUrl(Song record);

    /**
     * 根据id查询歌曲地址
     */
    public Song selectSongUrlById(Integer id);

    public List<Song> songOfSongName(String song_name);

    public List<Song> songOfSongUrl(String url);
}
