package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Singer;
import com.xuwei.music.service.SingerService;
import com.xuwei.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 歌手控制类
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */
    @PostMapping(value = "/add")
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addSingerInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim(); //姓名
        String sex = request.getParameter("sex").trim(); //性别
        String pic = request.getParameter("pic").trim(); //歌手图片
        String singer_birth = request.getParameter("singer_birth").trim(); //歌手生日
        String singer_location = request.getParameter("singer_location").trim(); //歌手籍贯
        String singer_intro = request.getParameter("singer_intro").trim(); //歌手简介
        //将生日的 string 类型数据转成 date 格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(singer_birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Integer(sex));
        singer.setPic(pic);
        singer.setSinger_birth(birthDate);
        singer.setSinger_location(singer_location);
        singer.setSinger_intro(singer_intro);
        //判断是否添加成功
        boolean flag = singerService.addSinger(singer);
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

    /**
     * 修改歌手
     */
    @PostMapping(value = "/update")
    public Object updateSingerInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim(); //id
        String name = request.getParameter("name").trim(); //姓名
        String sex = request.getParameter("sex").trim(); //性别
        String singer_birth = request.getParameter("singer_birth").trim(); //歌手生日
        String singer_location = request.getParameter("singer_location").trim(); //歌手籍贯
        String singer_intro = request.getParameter("singer_intro").trim(); //歌手简介
        //将生日的 string 类型数据转成 date 格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(singer_birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Integer(sex));
        singer.setSinger_birth(birthDate);
        singer.setSinger_location(singer_location);
        singer.setSinger_intro(singer_intro);
        //判断是否修改成功
        boolean flag = singerService.updateSinger(singer);
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
     * 删除
     */
    @DeleteMapping(value = "/delete")
    public Object deleteSingerInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        Singer singer = singerService.selectSingerById(Integer.parseInt(id));
        String picUrl = singer.getPic();
        System.out.println(picUrl);
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        try {
            singerService.deleteSinger(Integer.parseInt(id));
            File file = new File(filePath + picUrl);
            if (file.delete()) {
                log.info(file.getName() + "is deleted");
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

    /**
     * 根据id查询
     */
    @GetMapping(value = "/selectSingerById")
    public Object selectSingerById(HttpServletRequest request) {
        String id = request.getParameter("id").trim(); //id
        return singerService.selectSingerById(Integer.parseInt(id));
    }

    /**
     * 查询所有歌手
     */
    @GetMapping(value = "/allSinger")
    public Object allSinger(HttpServletRequest request) {
        return singerService.allSinger();
    }

    /**
     * 根据歌手名字模糊查询
     */
    @GetMapping(value = "/singerOfName")
    public Object singerOfName(HttpServletRequest request) {
        String name = request.getParameter("name").trim(); //名字
        return singerService.singerOfName("%" + name + "%");
    }



    /**
     * 根据性别查询
     */
    @GetMapping(value = "/singerOfSex")
    public Object singerOfSex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim(); //性别
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    /**
     * 上传歌手图片
     */
    @PostMapping(value = "/uploadSingerOfAvator")
    public Object uploadSingerOfAvator(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        //如果文件路径不存在新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.updateSingerImg(singer);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "文件上传成功");
                jsonObject.put("pic", storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }

}
