package com.xuwei.music.dao;

import com.xuwei.music.entity.Rank;
import org.springframework.stereotype.Repository;

@Repository
public interface RankDao {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 增加
     * @param record
     * @return
     */
    int insert(Rank record);

    int insertSelective(Rank record);

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
}