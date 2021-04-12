package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Song;
import com.xuwei.music.entity.SongList;
import com.xuwei.music.service.SongListService;
import com.xuwei.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌单管理类
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;


    /*添加歌单*/
    @PostMapping(value = "/add")
    public Object addSongListInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //歌单名字
        String title = request.getParameter("title").trim();
        //歌曲介绍
        String songlist_intro = request.getParameter("songlist_intro").trim();
        //歌单风格
        String style = request.getParameter("style").trim();
        //歌单制作者
        String author = request.getParameter("author").trim();
        //歌曲图片
        String pic = "/img/songListPic/songList.png";
        //保存到歌手的对象中
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setSonglist_intro(songlist_intro);
        songList.setPic(pic);
        songList.setStyle(style);
        songList.setAuthor(author);
        //判断是否添加成功
        boolean flag = songListService.insertSongList(songList);
        if (flag) {
            //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    /*更新歌单*/
    @PostMapping(value = "/update")
    public Object updateSongListInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        //歌单名字
        String title = request.getParameter("title").trim();
        //歌单介绍
        String songlist_intro = request.getParameter("songlist_intro").trim();
        //歌单风格
        String style = request.getParameter("style").trim();
        //歌单制作者
        String author = request.getParameter("author").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setStyle(style);
        songList.setSonglist_intro(songlist_intro);
        songList.setAuthor(author);
        boolean flag = songListService.updateSongList(songList);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
            return jsonObject;
        }
    }

    // 先查询到数据库中对应的文件地址，删除掉它，再进行下面的操作
    /*删除*/
    @DeleteMapping("/delete")
    public Object deleteSongListInfo(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = songListService.deleteSongList(Integer.parseInt(id));
        if (flag){
            return true;
        }
        return false;
    }

    /*更新歌曲图片*/
    @PostMapping(value = "/updateSongListListOfAvator")
    public Object updateSongListListOfAvator(@RequestParam("file") MultipartFile mpFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "图片上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongListPath = "/img/songListPic/" + fileName;
        try {
            mpFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeSongListPath);
            boolean flag = songListService.updateSongListImgById(songList);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeSongListPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "上传失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }


    /*根据歌手id查询歌曲*/
    @GetMapping(value = "/songListById")
    public Object songOfSingerId(HttpServletRequest request) {
        String id = request.getParameter("id");
        return songListService.selectById(Integer.parseInt(id));
    }

    /**
     * 查询所有歌单
     */
    @GetMapping(value = "/allSongList")
    public Object allSongList(HttpServletRequest request){
        return songListService.allSongList();
    }

    /**
     * 根据标题模糊查询歌单列表
     */
    @GetMapping(value = "/songListLikeTitle")
    public Object songListLikeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.songListLikeTitle("%"+title+"%");
    }

    /**
     * 根据风格模糊查询歌单列表
     */
    @GetMapping(value = "/songListLikeStyle")
    public Object  songListLikeStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();
        return songListService.songListLikeStyle("%"+style+"%");
    }

    /**
     * 根据标题精确查询
     */
    @GetMapping(value = "/songListOfTitle")
    public Object songListOfTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.songListOfTitle(title);
    }
}
