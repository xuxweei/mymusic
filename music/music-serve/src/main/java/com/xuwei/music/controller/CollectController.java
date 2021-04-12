package com.xuwei.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Collect;
import com.xuwei.music.service.CollectService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @PostMapping(value = "/add")
    public Object addCollect(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String user_id = request.getParameter("user_id"); //用户id
        String type = request.getParameter("type"); //类型
        String song_id = request.getParameter("song_id"); //歌曲id
//        String songlist_id = request.getParameter("songlist_id"); //歌单id
        if (song_id==null||song_id.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏歌曲为空");
            return jsonObject;
        }
//        if (songlist_id==null||songlist_id.equals("")){
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"收藏歌单为空");
//            return jsonObject;
//        }
        if (collectService.existSongId(Integer.parseInt(user_id),Integer.parseInt(song_id))){
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已收藏");
            return jsonObject;
        }

        Collect collect = new Collect();
        collect.setUser_id(Integer.parseInt(user_id));
        collect.setType(Integer.parseInt(type));
//        if (Integer.parseInt(type) == 0) {
            collect.setSong_id(Integer.parseInt(song_id));
//        } else {
//            collect.setSonglist_id(Integer.parseInt(songlist_id));
//        }
        boolean flag = collectService.insert(collect);
        if (flag) {
            //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "收藏失败");
        return jsonObject;
    }

    /**
     * 修改收藏
     */
   /* @PostMapping(value = "/update")
    public Object updateCollectInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id".trim()); //id
        String user_id = request.getParameter("user_id").trim(); //用户id
        String type = request.getParameter("type").trim(); //类型
        String song_id = request.getParameter("song_id").trim(); //歌曲id
        String songlist_id = request.getParameter("songlist_id").trim(); //歌单id
        //保存到收藏对象中
        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUser_id(Integer.parseInt(user_id));
        collect.setType(Integer.parseInt(type));
        if (song_id != null && song_id.equals("")) {
            song_id = null;
        } else {
            collect.setSong_id(Integer.parseInt(song_id));
        }
        if (songlist_id != null && songlist_id.equals("")) {
            songlist_id = null;
        } else {
            collect.setSonglist_id(Integer.parseInt(songlist_id));
        }
        //判断是否修改成功
        boolean flag = collectService.updateById(collect);
        if (flag) {
            //修改成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }*/

    /**
     * 删除收藏
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/deleteById")
    public Object deleteCollect(HttpServletRequest request) {
        String id = request.getParameter("id");
        return collectService.deleteById(Integer.parseInt(id));
    }

    /**
     * 删除收藏
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/deleteByUserIdAndSongId")
    public Object deleteByUserIdAndSongId(HttpServletRequest request) {
        String user_id = request.getParameter("user_id").trim(); //用户id
        String song_id = request.getParameter("song_id").trim(); //歌曲id
        return collectService.deleteByUserIdAndSongId(Integer.parseInt(user_id),Integer.parseInt(song_id));
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
        return collectService.selectById(Integer.parseInt(id));
    }

    /**
     * 查询所有收藏
     *
     * @return
     */
    @GetMapping(value = "/allCollect")
    public Object allCollect() {
        return collectService.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/collectOfUserId")
    public Object collectOfUserID(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        return collectService.collectOfUserId(Integer.parseInt(user_id));
    }

}
