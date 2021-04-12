package com.xuwei.music.service.impl;

import com.xuwei.music.dao.ListSongDao;
import com.xuwei.music.entity.ListSong;
import com.xuwei.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongDao listSongDao;

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return listSongDao.deleteById(id) > 0 ? true : false;
    }

    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(ListSong record) {
        return listSongDao.insert(record) > 0 ? true : false;
    }

    /**
     * 添加歌单歌曲
     *
     * @param record
     * @return
     */
    @Override
    public boolean insertListSong(ListSong record) {
        return listSongDao.insertListSong(record) > 0 ? true : false;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectById(Integer id) {
        return listSongDao.selectById(id);
    }

    /**
     * 更新歌单
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateListSong(ListSong record) {
        return listSongDao.updateListSong(record) > 0 ? true : false;
    }

    @Override
    public boolean updateByPrimaryKey(ListSong record) {
        return listSongDao.updateByPrimaryKey(record) > 0 ? true : false;
    }

    /**
     * 查询所有歌单里面的歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongDao.allListSong();
    }

    /**
     * 根据歌曲id查询歌单
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongDao.listSongOfSongListId(songListId);
    }

    /**
     * 根据song_id和songlist_id删除
     *
     * @param song_id
     * @param songlist_id
     */
    @Override
    public boolean deleteBySongIdAndSongListId(Integer song_id, Integer songlist_id) {
        return listSongDao.deleteBySongIdAndSongListId(song_id, songlist_id) > 0 ? true : false;
    }
}
