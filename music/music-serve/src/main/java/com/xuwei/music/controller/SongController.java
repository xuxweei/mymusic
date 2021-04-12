package com.xuwei.music.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Singer;
import com.xuwei.music.entity.Song;
import com.xuwei.music.service.SongService;
import com.xuwei.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Date;
import java.util.List;

/**
 * 歌曲管理类
 */
@RestController
@RequestMapping("/song")
public class SongController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SongService songService;


    /*添加歌曲*/
    @PostMapping(value = "/add")
    public Object addSongInfo(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {
        JSONObject jsonObject = new JSONObject();
        //歌手id
        String singer_id = request.getParameter("singer_id").trim();
        //歌曲名字
        String song_name = request.getParameter("song_name").trim();
        //歌曲介绍
        String song_intro = request.getParameter("song_intro").trim();
        //歌曲图片
        String song_pic = "/img/songPic/song.png";
        //歌词
        String lyric = request.getParameter("lyric").trim();

        //上传歌曲文件
        if (multipartFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        //如果文件路径不存在新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/song/" + fileName;
        try {
            multipartFile.transferTo(dest);
            Song song = new Song();
            song.setSinger_id(Integer.parseInt(singer_id));
            song.setSong_name(song_name);
            song.setSong_intro(song_intro);
            song.setCreate_time(new Date());
            song.setUpdate_time(new Date());
            song.setSong_pic(song_pic);
            song.setLyric(lyric);
            song.setUrl(storeSongPath);
            boolean flag = songService.addSong(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "保存成功");
                jsonObject.put("url", storeSongPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "保存失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }

    /*更新歌曲*/
    @PostMapping(value = "/update")
    public Object updateSongInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        //歌手id
        String singer_id = request.getParameter("singer_id").trim();
        //歌曲名字
        String song_name = request.getParameter("song_name").trim();
        //歌曲介绍
        String song_intro = request.getParameter("song_intro").trim();
        //歌词
        String lyric = request.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSinger_id(Integer.parseInt(singer_id));
        song.setSong_name(song_name);
        song.setSong_intro(song_intro);
        song.setUpdate_time(new Date());
        song.setLyric(lyric);
        boolean flag = songService.updateSong(song);
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
    public Object deleteSongInfo(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Song song = songService.selectSongById(Integer.parseInt(id));
        String url = song.getUrl();
        String songPic = song.getSong_pic();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        System.out.println(url);
        System.out.println(songPic);
        try {
            songService.deleteSong(Integer.parseInt(id));
            File file = new File(filePath + url);
            File file1 = new File(filePath + songPic);
            if (file.delete() && file1.delete()) {
                log.info(file.getName() + "+" + file1.getName() + "is deleted");
                return "true";
            } else {
                log.info("Delete failed.");
                return "false";
            }
        } catch (Exception e) {
            log.info("Exception occured");
            e.printStackTrace();
            return "false";
        }
    }

    /*更新歌曲图片*/
    @PostMapping(value = "/updateSongOfAvator")
    public Object updateSongOfAvator(@RequestParam("file") MultipartFile mpFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "图片上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/img/songPic/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setSong_pic(storeSongPath);
            boolean flag = songService.updateSongImg(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("pic", storeSongPath);
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

    /*更新歌曲url*/
    //需在上传前找到原来的文件进行删除再上传
    @PostMapping(value = "/updateSongOfUrl")
    public Object updateSongOfUrl(@RequestParam("file") MultipartFile mpFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "音乐上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        File file1 = new File(filePath+ "song");
        Song song1 = songService.selectSongById(id);
        String url = song1.getUrl();
        System.out.println(url);
        try {
            File del = new File(filePath+url);
            if (del.delete()) {
                log.info(del.getName() + "is deleted");
            } else {
                log.info("Delete failed.");
            }
        } catch (Exception e) {
            log.info("Exception occured");
            e.printStackTrace();
        }
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath +"song"+ System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/song/" + fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeSongPath);
            boolean flag = songService.updateSongUrl(song);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("song", storeSongPath);
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
    @GetMapping(value = "/singer/detail")
    public Object songOfSingerId(HttpServletRequest request) {
        String singer_id = request.getParameter("singer_id");
        return songService.songOfSingerId(Integer.parseInt(singer_id));
    }

    /*根据歌曲id查询歌曲*/
    @GetMapping(value = "/detail")
    public Object songOfSongId(HttpServletRequest request) {
        String song_id = request.getParameter("song_id");
        return songService.selectSongById(Integer.parseInt(song_id));
    }

    /*根据歌曲名查询歌曲*/
    @GetMapping(value = "/songNameDetail")
    public Object songOfSongName(HttpServletRequest request) {
        String song_name = request.getParameter("song_name");
        return songService.songOfSongName(song_name);
    }

    /*根据歌曲名查询歌曲*/
    @GetMapping(value = "/likeSongOfName")
    public Object likeSongOfName(HttpServletRequest request) {
        String song_name = request.getParameter("song_name");
        return songService.likeSongOfName(song_name);
    }
    /*根据歌曲名查询歌曲*/
    @GetMapping(value = "/likeSongOfIntro")
    public Object likeSongOfIntro(HttpServletRequest request) {
        String song_intro = request.getParameter("song_intro");
        return songService.likeSongOfIntro(song_intro);
    }


    /*根据歌曲id查询歌曲*/
    @GetMapping(value = "/allSong")
    public Object allSong(HttpServletRequest request) {
        return songService.allSong();
    }

    @GetMapping(value = "/songOfSongUrl")
    public Object songOfSongUrl(HttpServletRequest request) {
        String url = request.getParameter("url").trim();
        System.out.println("22222");
        return songService.songOfSongUrl(url);
    }

}
