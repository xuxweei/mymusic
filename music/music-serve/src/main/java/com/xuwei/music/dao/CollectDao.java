package com.xuwei.music.dao;

import com.xuwei.music.entity.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectDao {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据用户id和歌曲id删除收藏歌曲
     * @param user_id
     * @param song_id
     * @return
     */
    int deleteByUserIdAndSongId(Integer user_id,Integer song_id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Collect record);

    int insertSelective(Collect record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Collect selectById(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByIdSelective(Collect record);

    int updateById(Collect record);

    /**
     * 查询所有收藏
     * @return
     */
    List<Collect> allCollect();

    /**
     * 查询某个用户的收藏列表
     * @param user_id
     * @return
     */
    List<Collect> collectOfUserId(Integer user_id);

    /**
     * 查询用户是否已经收藏了某个歌曲
     * @param song_id
     * @return
     */
    int existSongId(@Param("user_id")Integer user_id, @Param("song_id")Integer song_id);

}