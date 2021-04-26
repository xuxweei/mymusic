package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.entity.Consumer;
import com.xuwei.music.entity.Up;
import com.xuwei.music.service.ConsumerService;
import com.xuwei.music.service.ConsumerService;
import com.xuwei.music.service.UpService;
import com.xuwei.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端用户控制类
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private UpService upService;

    /**
     * 添加歌手
     */
    @PostMapping(value = "/add")
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Object addConsumerInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim(); //姓名
        String password = request.getParameter("password").trim(); //密码
        String sex = request.getParameter("sex").trim(); //性别
        String phone_num = request.getParameter("phone_num").trim(); //手机号码
        String email = request.getParameter("email").trim(); //电子邮箱
        String birth = request.getParameter("birth").trim(); //用户生日
        String introduction = request.getParameter("introduction").trim(); //用户介绍
        String location = request.getParameter("location").trim(); //用户地区
        String userpic = request.getParameter("userpic").trim(); //用户头像

        if (username == null || username.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        Consumer consumerUserName = consumerService.consumerByUserName(username);
        if (consumerUserName != null) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名已存在");
            return jsonObject;
        }
        if (password == null || password.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }


        //将生日的 string 类型数据转成 date 格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Integer(sex));
        consumer.setPhone_num(phone_num);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setUserpic(userpic);
        //判断是否添加成功
        boolean flag = consumerService.insertConsumer(consumer);
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
     * 修改用户
     */
    @PostMapping(value = "/update")
    public Object updateConsumerInfo(HttpServletRequest request) {
        //返回json数据
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim(); //id
        String username = request.getParameter("username").trim(); //姓名
        String password = request.getParameter("password").trim(); //密码
        String sex = request.getParameter("sex").trim(); //性别
        String phone_num = request.getParameter("phone_num").trim(); //手机号码
        String email = request.getParameter("email").trim(); //电子邮箱
        String birth = request.getParameter("birth").trim(); //用户生日
        String introduction = request.getParameter("introduction").trim(); //用户介绍
        String location = request.getParameter("location").trim(); //用户地区

        if (username == null || username.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (password == null || password.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }

        //将生日的 string 类型数据转成 date 格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(username);
        consumer.setSex(new Integer(sex));
        consumer.setPhone_num(phone_num);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        //判断是否修改成功
        boolean flag = consumerService.updateUserMsg(consumer);
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
    public Object deleteConsumerInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        boolean flag = consumerService.deleteByPrimaryKey(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/selectConsumerById")
    public Object selectConsumerById(HttpServletRequest request) {
        String id = request.getParameter("id").trim(); //id
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 根据id查询
     */
    @GetMapping(value = "/consumerOfId")
    public Object consumerOfId(HttpServletRequest request) {
        String id = request.getParameter("id").trim(); //id
        return consumerService.consumerOfId(Integer.parseInt(id));
    }

    /**
     * 查询所有用户
     */
    @GetMapping(value = "/allConsumer")
    public Object allConsumer(HttpServletRequest request) {
        return consumerService.allConsumer();
    }


    /**
     * 根据用户名查询
     */

    @GetMapping(value = "/consumerByUserName")
    public Object consumerByUserName(HttpServletRequest request) {
        String username = request.getParameter("username").trim(); //性别
        Object sb = consumerService.consumerByName(username);
        System.out.println("11111滴滴滴");
        System.out.println(sb);
        return sb;
    }


    /**
     * 登录
     *
     * @param request
     * @param session
     * @return
     */
    @PostMapping(value = "login")
    public Object login(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim(); //姓名
        String password = request.getParameter("password").trim(); //密码
        System.out.println(username + "  " + password);
        if (username == null || username.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (password == null || password.equals("")) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean res = consumerService.verifyPassword(username, password);
        if (res) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "登录成功");
            jsonObject.put("userMsg", consumerService.consumerByUserName(username));
            session.setAttribute("uid",consumerService.consumerByUserName(username).getId());
            System.out.println(consumerService.consumerByUserName(username).getId());
            session.setAttribute("username", username);
            return jsonObject;
        } else {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "用户名或密码错误");
            return jsonObject;
        }
    }

    /**
     * 上传用户图片
     */
    @PostMapping(value = "/uploadConsumerOfAvator")
    public Object uploadConsumerOfAvator(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
        //如果文件路径不存在新增该路径
        File file1 = new File(filePath + "img"
                + System.getProperty("file.separator") + "consumerPic");
        Consumer consumerPic = consumerService.selectByPrimaryKey(id);
        String url = consumerPic.getUserpic();
        System.out.println(url);
        try {
            File del = new File(filePath + url);
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
        //实际的文件地址
        File dest = new File(filePath + "img"
                + System.getProperty("file.separator") + "consumerPic" + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/consumerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setUserpic(storeAvatorPath);
            boolean flag = consumerService.updateUserAvator(consumer);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "文件上传成功");
                jsonObject.put("consumer_pic", storeAvatorPath);
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
