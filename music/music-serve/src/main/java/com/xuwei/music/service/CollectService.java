package com.xuwei.music.service;

import com.xuwei.music.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收藏的service接口
 */
public interface CollectService {

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 根据用户id和歌曲id删除收藏歌曲
     * @param user_id
     * @param song_id
     * @return
     */
    boolean deleteByUserIdAndSongId(Integer user_id,Integer song_id);

    /**
     * 添加
     * @param record
     * @return
     */
    boolean insert(Collect record);

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
    boolean updateById(Collect record);

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
    boolean existSongId(@Param("user_id")Integer user_id, @Param("song_id")Integer song_id);

}
