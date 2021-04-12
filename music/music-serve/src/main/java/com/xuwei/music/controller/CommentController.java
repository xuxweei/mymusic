package com.xuwei.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.*;
import com.xuwei.music.entity.Comment;
import com.xuwei.music.service.CommentService;
import com.xuwei.music.service.ConsumerService;
import com.xuwei.music.service.UpService;
import com.xuwei.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论控制类
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommentService commentService;
    @Autowired
    private UpService upService;
    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加评论
     */
    @PostMapping(value = "/add")
    public Object addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String user_id = request.getParameter("user_id"); //用户id
        String type = request.getParameter("type"); //类型
        String song_id = request.getParameter("song_id"); //歌曲id
        String songlist_id = request.getParameter("songlist_id"); //歌单id
//        String create_time = request.getParameter("create_time").trim(); //创建时间
        String content = request.getParameter("content").trim(); //评论内容
//        String up = request.getParameter("up").trim();
        Comment comment = new Comment();
        comment.setUser_id(Integer.parseInt(user_id));
        comment.setType(Integer.parseInt(type));
        if (Integer.parseInt(type) == 0) {
            comment.setSong_id(Integer.parseInt(song_id));
        } else {
            comment.setSonglist_id(Integer.parseInt(songlist_id));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if (flag) {
            //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @PostMapping(value = "/update")
    public Object updateCommentInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id".trim()); //id
        String user_id = request.getParameter("user_id").trim(); //用户id
        String type = request.getParameter("type").trim(); //类型
        String song_id = request.getParameter("song_id").trim(); //歌曲id
        String songlist_id = request.getParameter("songlist_id").trim(); //歌单id
//        String create_time = request.getParameter("create_time").trim(); //创建时间
        String content = request.getParameter("content").trim(); //评论内容
        String up = request.getParameter("up").trim();
        //保存到评论对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUser_id(Integer.parseInt(user_id));
        comment.setType(Integer.parseInt(type));
        if (song_id != null && song_id.equals("")) {
            song_id = null;
        } else {
            comment.setSong_id(Integer.parseInt(song_id));
        }
        if (songlist_id != null && songlist_id.equals("")) {
            songlist_id = null;
        } else {
            comment.setSonglist_id(Integer.parseInt(songlist_id));
        }
        comment.setContent(content);
        comment.setUp(Integer.parseInt(up));
        //判断是否修改成功
        boolean flag = commentService.updateById(comment);
        if (flag) {
            //修改成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/delete")
    public Object deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id");
        return commentService.deleteById(Integer.parseInt(id));
    }

    /**
     * 根据id查询
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/selectById")
    public Object selectById(HttpServletRequest request) {
        String id = request.getParameter("id");
        return commentService.selectById(Integer.parseInt(id));
    }

    /**
     * 查询所有评论
     *
     * @return
     */
    @GetMapping(value = "/allComment")
    public List allComment() {
        return commentService.allComment();
    }

    /**
     * 获得指定歌曲id的评论列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/commentOfSongId")
    public Object commentOfSongID(HttpServletRequest request) {
        String song_id = request.getParameter("song_id");
        return commentService.commentOfSongID(Integer.parseInt(song_id));
    }

    /**
     * 获得指定歌单id的评论列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/commentOfSongListId")
    public List commentOfSongListID(HttpServletRequest request) {
        List<Comment> cmt = new ArrayList<>();
        HttpSession session = request.getSession();
        String songlist_id = request.getParameter("songlist_id");
        String consumer_id = request.getParameter("consumer_id");
        Integer conid = Integer.parseInt(consumer_id);
        Integer slid = Integer.parseInt(songlist_id);
        for (Comment ct : commentService.commentOfSongListID(slid)){
            System.out.println("评论"+ct);
            for (Up p : upService.getLikedByCommentId(ct.getId(),conid)){
//                System.out.println(session.getAttribute("userId"));
                System.out.println("状态"+p.getLike_status());
                ct.setLike_status(p.getLike_status());
            }
            cmt.add(ct);
        }
        return cmt;
    }

    /**
     * 点赞
     * @param request
     * @return
     */
    @PostMapping(value = "/like")
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id".trim()); //id
        String up = request.getParameter("up").trim(); //点赞
        //保存到评论对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        //判断是否修改成功
        boolean flag = commentService.updateById(comment);
        if (flag) {
            //修改成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }
}
