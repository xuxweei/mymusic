package com.xuwei.music.service.impl;

import com.xuwei.music.dao.CommentDao;
import com.xuwei.music.entity.Comment;
import com.xuwei.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论service实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Integer id) {
        return commentDao.deleteById(id) > 0 ? true : false;
    }

    /**
     * 增加
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Comment record) {
        return commentDao.insert(record) > 0 ? true : false;
    }

    @Override
    public boolean insertSelective(Comment record) {
        return commentDao.insertSelective(record) > 0;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Comment selectById(Integer id) {
        return commentDao.selectById(id);
    }

    /**
     * 更新评论
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateById(Comment record) {
        return commentDao.updateById(record) > 0 ? true : false;
    }

    @Override
    public boolean updateByIdSelective(Comment record) {
        return commentDao.updateByIdSelective(record) > 0 ? true : false;
    }

    /**
     * 查询所有评论
     *
     * @return
     */
    @Override
    public List<Comment> allComment() {
        return commentDao.allComment();
    }

    /**
     * 查询某一首歌的评论
     *
     * @param songId
     * @return
     */
    @Override
    public List<Comment> commentOfSongID(Integer songId) {
        return commentDao.commentOfSongID(songId);
    }

    /**
     * 查询某歌单的评论
     *
     * @param songListId
     * @return
     */
    @Override
    public List<Comment> commentOfSongListID(Integer songListId) {
        return commentDao.commentOfSongListID(songListId);
    }

    /**
     * 查询某动态的评论
     *
     * @param community_id
     * @return
     */
    @Override
    public List<Comment> commentOfCommunityID(Integer community_id) {
        return commentDao.commentOfCommunityID(community_id);
    }
}
