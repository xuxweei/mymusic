package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Rank;
import com.xuwei.music.service.RankService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;

    /**
     * 添加评价
     */
    @PostMapping(value = "/add")
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //歌手id
        String songlist_id = request.getParameter("songlist_id").trim();
        String consumer_id = request.getParameter("consumer_id").trim();
        String score = request.getParameter("score").trim();
        Rank rank = new Rank();
        rank.setSonglist_id(Integer.parseInt(songlist_id));
        rank.setConsumer_id(Integer.parseInt(consumer_id));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insert(rank);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评价成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @GetMapping(value = "/average")
    public Object average(HttpServletRequest request) {
        String songlist_id = request.getParameter("songlist_id").trim();
        return rankService.rankOfSongListId(Integer.parseInt(songlist_id));
    }
}
