package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Community;
import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.entity.Up;
import com.xuwei.music.service.CommunityUpService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/communityUp")
public class CommunityUpController {
    @Autowired
    private CommunityUpService communityUpService;

    /**
     * 动态点赞
     */
    @PostMapping(value = "/communityLike")
    public Object communityLike(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String consumer_id = request.getParameter("consumer_id");
        String community_id = request.getParameter("community_id");

        CommunityUp communityUp = new CommunityUp();
        communityUp.setConsumerId(Integer.parseInt(consumer_id));
        communityUp.setCommunityId(Integer.parseInt(community_id));

        System.out.println("点赞：" + communityUp.toString());

        boolean flag = communityUpService.insert(communityUp);

        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }


    /**
     * 评论点赞
     */
    @PostMapping(value = "/commentLike")
    public Object commentLike(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String consumer_id = request.getParameter("consumer_id");
        String comment_son_id = request.getParameter("comment_son_id");
        String community_id = request.getParameter("community_id");
        CommunityUp communityUp = new CommunityUp();
        communityUp.setConsumerId(Integer.parseInt(consumer_id));
        communityUp.setCommentSonId(Integer.parseInt(comment_son_id));
        communityUp.setCommunityId(Integer.parseInt(community_id));

        System.out.println("点赞：" + communityUp.toString());
        boolean flag = communityUpService.insert(communityUp);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }

    /**
     * 删除动态点赞
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Object deleteCommunityUp(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return communityUpService.deleteCommunity(Integer.parseInt(id));
    }

    /**
     * 点赞
     */
    @GetMapping(value = "/getLikes")
    public Object getLikes(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        return communityUpService.getLikes(Integer.parseInt(consumer_id));
    }

    /**
     * 获取动态的评论点赞
     */
    @GetMapping(value = "/getLikedOfComment")
    public Object getLikedOfComment(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        String community_id = request.getParameter("community_id").trim();
        return communityUpService.getLikedOfComment(Integer.parseInt(community_id),Integer.parseInt(consumer_id));
    }
    /**
     * 获取动态点赞
     */
    @GetMapping(value = "/getLikedOfCommunity")
    public Object getLikedOfCommunity(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        return communityUpService.getLikedOfCommunity(Integer.parseInt(consumer_id));
    }


}
