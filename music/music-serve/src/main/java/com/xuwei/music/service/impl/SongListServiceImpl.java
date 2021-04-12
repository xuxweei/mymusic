package com.xuwei.music.service.impl;

import com.xuwei.music.dao.SongListDao;
import com.xuwei.music.entity.SongList;
import com.xuwei.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单service实现类
 */
@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListDao songListDao;
    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insertSongList(SongList record) {
        return songListDao.insertSongList(record) > 0 ?true:false;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteSongList(Integer id) {
        return songListDao.deleteSongList(id)> 0 ?true:false;
    }

    /**
     * 修改
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateSongList(SongList record) {
        return songListDao.updateSongList(record) > 0?true:false;
    }

    /**
     * 修改歌单图片
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateSongListImgById(SongList record) {
        return songListDao.updateSongListImgById(record)>0?true:false;
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectById(Integer id) {
        return songListDao.selectById(id);
    }

    /**
     * 查询所有歌单
     *
     * @return
     */
    @Override
    public List<SongList> allSongList() {
        return songListDao.allSongList();
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListLikeTitle(String title) {
        return songListDao.songListLikeTitle(title);
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> songListLikeStyle(String style) {
        return songListDao.songListLikeStyle(style);
    }

    /**
     * 根据标题精确查询
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListDao.songListOfTitle(title);
    }
}
