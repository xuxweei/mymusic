package com.xuwei.music.service.impl;

import com.xuwei.music.dao.CollectDao;
import com.xuwei.music.entity.Collect;
import com.xuwei.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏service实现类
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return collectDao.deleteById(id) > 0 ? true : false;
    }

    /**
     * 根据用户id和歌曲id删除收藏歌曲
     *
     * @param user_id
     * @param song_id
     * @return
     */
    @Override
    public boolean deleteByUserIdAndSongId(Integer user_id, Integer song_id) {
        return collectDao.deleteByUserIdAndSongId(user_id, song_id) > 0 ? true : false;
    }

    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Collect record) {
        return collectDao.insert(record) > 0 ? true : false;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Collect selectById(Integer id) {
        return collectDao.selectById(id);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateById(Collect record) {
        return collectDao.updateById(record) > 0 ? true : false;
    }

    /**
     * 查询所有收藏
     *
     * @return
     */
    @Override
    public List<Collect> allCollect() {
        return collectDao.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param user_id
     * @return
     */
    @Override
    public List<Collect> collectOfUserId(Integer user_id) {
        return collectDao.collectOfUserId(user_id);
    }

    /**
     * 查询用户是否已经收藏了某个歌曲
     *
     * @param user_id
     * @param song_id
     * @return
     */
    @Override
    public boolean existSongId(Integer user_id, Integer song_id) {
        return collectDao.existSongId(user_id, song_id) > 0 ? true : false;
    }

}
