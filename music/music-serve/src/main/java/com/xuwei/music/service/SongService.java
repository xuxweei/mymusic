package com.xuwei.music.service;

import com.xuwei.music.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲Service接口
 */
public interface SongService {
    /**增加*/
    public boolean addSong(Song song);

    /**修改*/
    public boolean updateSong(Song song);

    /**
     * 删除
     */
    public boolean deleteSong(Integer id);

    /**根据id查询*/
    public Song selectSongById(Integer id);

    /**查询所有歌曲*/
    public List<Song> allSong();

    /**根据歌曲名字模糊查询*/
    public List<Song> likeSongOfName(String name);
    /**根据专辑名字模糊查询*/
    public List<Song> likeSongOfIntro(String intro);
    /**根据歌曲名字模糊查询*/
    public List<Song> songOfSongName(String song_name);

    /**根据歌手id查询*/
    public List<Song> songOfSingerId(Integer singerId);

    /**修改歌手图片*/
    public boolean updateSongImg(Song song);

    /**修改歌曲地址*/
    public boolean updateSongUrl(Song song);

    /**
     * 根据id查询歌曲地址
     */
    public Song selectSongUrlById(Integer id);

    public List<Song> songOfSongUrl(String url);

}
