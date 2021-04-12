package com.xuwei.music.service;

import com.xuwei.music.entity.Comment;

import java.util.List;

/**
 * 评论service接口
 */
public interface CommentService {
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
    boolean insert(Comment record);

    boolean insertSelective(Comment record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Comment selectById(Integer id);

    /**
     * 更新评论
     * @param record
     * @return
     */
    boolean updateById(Comment record);

    boolean updateByIdSelective(Comment record);

    /**
     * 查询所有评论
     * @return
     */
    List<Comment> allComment();

    /**
     * 查询某一首歌的评论
     * @param songId
     * @return
     */
    List<Comment> commentOfSongID(Integer songId);

    /**
     * 查询某歌单的评论
     * @param songListId
     * @return
     */
    List<Comment> commentOfSongListID(Integer songListId);
}
