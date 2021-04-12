package com.xuwei.music.service.impl;

import com.xuwei.music.dao.SingerDao;
import com.xuwei.music.entity.Singer;
import com.xuwei.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手service实现类
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerDao singerDao;

    /**
     * 增加
     *
     * @param singer
     */
    @Override
    public boolean addSinger(Singer singer) {
        int result = singerDao.addSinger(singer);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public boolean updateSinger(Singer singer) {
        int result = singerDao.updateSinger(singer);
        if (result > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean deleteSinger(Integer id) {
        return singerDao.deleteSinger(id)>0?true:false;
    }


    /**
     * 根据id查询
     *
     * @param id
     */
    @Override
    public Singer selectSingerById(Integer id) {
        return singerDao.selectSingerById(id);
    }

    /**
     * 查询所有歌手
     */
    @Override
    public List<Singer> allSinger() {
        return singerDao.allSinger();
    }

    /**
     * 根据歌手名字模糊查询
     *
     * @param name
     */
    @Override
    public List<Singer> singerOfName(String name) {
        return singerDao.singerOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerDao.singerOfSex(sex);
    }

    /**
     * 修改图片
     *
     * @param singer
     */
    @Override
    public boolean updateSingerImg(Singer singer) {
        return singerDao.updateSingerImg(singer) > 0 ? true : false;
    }
}
