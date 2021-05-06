package com.xuwei.music.dao;

import com.xuwei.music.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
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
    int insert(Comment record);

    int insertSelective(Comment record);

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
    int updateById(Comment record);

    int updateByIdSelective(Comment record);

    /**
     * 查询所有评论
     * @return
     */
    List<Comment> allComment();


    /**
     * 查询某歌单的评论
     * @param songListId
     * @return
     */
    List<Comment> commentOfSongListID(Integer songListId);

    /**
     * 查询某动态的评论
     * @param community_id
     * @return
     */
    List<Comment> commentOfCommunityID(Integer community_id);
}