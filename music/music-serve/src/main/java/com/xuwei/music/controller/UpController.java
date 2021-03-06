package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Comment;
import com.xuwei.music.entity.Rank;
import com.xuwei.music.entity.Up;
import com.xuwei.music.service.CommentService;
import com.xuwei.music.service.UpService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/up")
public class UpController {
    @Autowired
    private UpService upService;

    /**
     * 点赞
     */
    @PostMapping(value = "/like")
    public Object like(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String consumer_id = request.getParameter("consumer_id");
        String comment_id = request.getParameter("comment_id");
//        String like_status = request.getParameter("like_status");
        Up up = new Up();
        up.setConsumer_id(Integer.parseInt(consumer_id));
        up.setComment_id(Integer.parseInt(comment_id));
//        up.setLike_status(Integer.parseInt(like_status));
        System.out.println("点赞：" + up.toString());
        boolean flag;
        Up checked = upService.checkedUp(up);
        if (checked==null){
            System.out.println("未点赞");
            up.setLike_status(1);
            flag = upService.insert(up);
        }else {
            System.out.println("已点赞");
            if (checked.getLike_status() == 0){
                up.setLike_status(1);
            }else {
                up.setLike_status(0);
            }
            flag = upService.update(up);
        }
        if (flag) {
            if (up.getLike_status() == 0 ){
                up.setLike_status(-1);
            }
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }

    /**
     * 点赞
     */
    @GetMapping(value = "/getLiked")
    public Object getLiked(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        return upService.getLiked(Integer.parseInt(consumer_id));
    }

    /**
     * 点赞
     */
    @GetMapping(value = "/getLikedById")
    public Object getLikedById(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        String comment_id = request.getParameter("comment_id").trim();
        return upService.getLikedByCommentId(Integer.parseInt(consumer_id),Integer.parseInt(comment_id));
    }


}
