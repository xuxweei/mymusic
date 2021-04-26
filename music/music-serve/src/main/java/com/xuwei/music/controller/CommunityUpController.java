package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.service.CommunityUpService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/communityUp")
public class CommunityUpController {

    @Autowired
    private CommunityUpService communityUpService;

    /**
     * 点赞
     */
    @PostMapping(value = "/like")
    public Object like(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String consumer_id = request.getParameter("consumer_id");
        String community_id = request.getParameter("community_id");
//        String like_status = request.getParameter("like_status");
        CommunityUp communityUp = new CommunityUp();
        communityUp.setConsumer_id(Integer.parseInt(consumer_id));
        communityUp.setCommunity_id(Integer.parseInt(community_id));
//        communityUp.setLike_status(Integer.parseInt(like_status));
        System.out.println("点赞：" + communityUp.toString());
        boolean flag;
        CommunityUp checked = communityUpService.checkedUp(communityUp);
        if (checked==null){
            System.out.println("未点赞");
            communityUp.setLike_status(1);
            flag = communityUpService.insert(communityUp);
        }else {
            System.out.println("已点赞");
            if (checked.getLike_status() == 0){
                communityUp.setLike_status(1);
            }else {
                communityUp.setLike_status(0);
            }
            flag = communityUpService.update(communityUp);
        }
        if (flag) {
            if (communityUp.getLike_status() == 0 ){
                communityUp.setLike_status(-1);
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
        return communityUpService.getLiked(Integer.parseInt(consumer_id));
    }

    /**
     * 点赞
     */
    @GetMapping(value = "/getLikedById")
    public Object getLikedById(HttpServletRequest request) {
        String consumer_id = request.getParameter("consumer_id").trim();
        String community_id = request.getParameter("community_id").trim();
        return communityUpService.getLikedByCommunityId(Integer.parseInt(community_id),Integer.parseInt(consumer_id));
    }


}
