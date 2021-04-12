package com.xuwei.music.service;

import com.xuwei.music.entity.Rank;

/**
 * 评价service接口
 */
public interface RankService {
    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 增加
     * @param record
     * @return
     */
    boolean insert(Rank record);

    Rank selectById(Integer id);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    int selectRankNum(Integer songListId);

    int rankOfSongListId(Integer songListId);
}
