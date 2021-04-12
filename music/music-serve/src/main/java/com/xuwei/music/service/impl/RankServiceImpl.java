package com.xuwei.music.service.impl;

import com.xuwei.music.dao.RankDao;
import com.xuwei.music.entity.Rank;
import com.xuwei.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评价service实现
 */
@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankDao rankDao;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return rankDao.deleteById(id) > 0 ? true : false;
    }

    /**
     * 增加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Rank record) {
        return rankDao.insert(record) > 0 ? true : false;
    }

    @Override
    public Rank selectById(Integer id) {
        return rankDao.selectById(id);
    }

    /**
     * 查总分
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankDao.selectScoreSum(songListId);
    }

    /**
     * 查总评分人数
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankDao.selectRankNum(songListId);
    }

    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = rankDao.selectRankNum(songListId);
        if (rankNum == 0) {
            return 5;
        }
        return rankDao.selectScoreSum(songListId) / rankNum;
    }
}
