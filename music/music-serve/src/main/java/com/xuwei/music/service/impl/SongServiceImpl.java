package com.xuwei.music.service.impl;

import com.xuwei.music.dao.SongDao;
import com.xuwei.music.entity.Song;
import com.xuwei.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲service实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDao songDao;

    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean addSong(Song song) {
        return songDao.addSong(song) > 0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean updateSong(Song song) {
        return songDao.updateSong(song) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean deleteSong(Integer id) {
        return songDao.deleteSong(id) > 0 ? true : false;
    }


    /**
     * 根据id查询
     *
     * @param id
     */
    @Override
    public Song selectSongById(Integer id) {
        return songDao.selectSongById(id);
    }

    /**
     * 查询所有歌曲
     */
    @Override
    public List<Song> allSong() {
        return songDao.allSong();
    }

    /**
     * 根据歌曲名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> likeSongOfName(String name) {
        return songDao.likeSongOfName("%"+name+"%");
    }

    /**
     * 根据专辑名字模糊查询
     *
     * @param intro
     */
    @Override
    public List<Song> likeSongOfIntro(String intro) {
        return songDao.likeSongOfIntro("%"+intro+"%");
    }


    /**
     * 根据歌曲名字精准查询
     *
     * @param song_name
     */
    @Override
    public List<Song> songOfSongName(String song_name) {
        return songDao.songOfSongName(song_name);
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songDao.songOfSingerId(singerId);
    }


    /**
     * 修改歌手图片
     *
     * @param song
     */
    @Override
    public boolean updateSongImg(Song song) {
        return songDao.updateSongImg(song) > 0 ? true : false;
    }

    /**
     * 更新歌曲地址
     * @param song
     * @return
     */
    @Override
    public boolean updateSongUrl(Song song) {
        return songDao.updateSongUrl(song) > 0 ? true : false;
    }

    /**
     * 根据id查询歌曲地址
     *
     * @param id
     */
    @Override
    public Song selectSongUrlById(Integer id) {
        return songDao.selectSongUrlById(id);
    }

    @Override
    public List<Song> songOfSongUrl(String url) {
        return songDao.songOfSongUrl(url);
    }
}
