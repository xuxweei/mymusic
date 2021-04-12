package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.ListSong;
import com.xuwei.music.service.ListSongService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 歌单歌曲控制类
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 根据id删除
     */
    @DeleteMapping(value = "/delete")
    public Object deleteById(HttpServletRequest request) {
        String song_id = request.getParameter("song_id");
        String songlist_id = request.getParameter("songlist_id");
        return listSongService.deleteBySongIdAndSongListId(Integer.parseInt(song_id),Integer.parseInt(songlist_id));

    }

    /*添加歌单歌曲*/
    @PostMapping(value = "/add")
    public Object addListSongInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //歌曲id
        String song_id = request.getParameter("song_id").trim();
        //歌曲id
        String songlist_id = request.getParameter("songlist_id").trim();

        ListSong listSong = new ListSong();
        listSong.setSong_id(Integer.parseInt(song_id));
        listSong.setSonglist_id(Integer.parseInt(songlist_id));
        boolean flag = listSongService.insertListSong(listSong);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "保存失败");
        return jsonObject;
    }

    /**
     * 查询所有歌单里面的歌曲
     */
    @GetMapping(value = "/allListSong")
    public Object allListSong() {
        return listSongService.allListSong();
    }

    /**
     * 根据歌曲id查询歌单
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest req) {
        String songlist_id = req.getParameter("songlist_id");
        return listSongService.listSongOfSongListId(Integer.parseInt(songlist_id));
    }
}
