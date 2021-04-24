package com.xuwei.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuwei.music.dto.FileUploadDto;
import com.xuwei.music.entity.Community;
import com.xuwei.music.entity.CommunityUp;
import com.xuwei.music.entity.Consumer;
import com.xuwei.music.entity.Song;
import com.xuwei.music.form.FileUploadForm;
import com.xuwei.music.service.*;
import com.xuwei.music.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 社区管理类
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CommunityService communityService;

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private SongService songService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CommunityUpService communityUpService;

    /*添加动态*/
    @PostMapping(value = "/add")
    public Object addCommunity(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        //用户名
        String name = request.getParameter("name").trim();
        //上传类型
        String type = request.getParameter("type").trim();
        //地址
        String url = request.getParameter("url").trim();
        //动态内容
        String content = request.getParameter("content").trim();
        //图片
        String img = request.getParameter("img").trim();
        //点赞
//        String up = request.getParameter("up").trim();

        //保存到社区的对象中
        Community community = new Community();
        community.setName(name);
        community.setType(Integer.parseInt(type));
        if (url != null && url.equals("")) {
            url = null;
        } else {
            community.setUrl(url);
        }
        if (content != null && content.equals("")) {
            content = null;
        } else {
            community.setContent(content);
        }
        if (img != null && img.equals("")) {
            img = null;
        } else {
            community.setImg(img);
        }
        //判断是否添加成功
        boolean flag = communityService.insert(community);
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

    @PostMapping(value = "/addWithUrl")
    @CrossOrigin
    public FileUploadDto addCommunityWithUrl(@ModelAttribute FileUploadForm form, HttpServletRequest request) {

        System.out.println(form);
        //文件在本地的路径
        String path = fileService.writeFile(form.getFile(), form.getType());
        JSONObject jsonObject = new JSONObject();
        //用户名
        String name = request.getParameter("name").trim();
        //上传类型
        String type = request.getParameter("type").trim();
        //地址
//        String url = request.getParameter("url").trim();
        //动态内容
        String content = request.getParameter("content").trim();
        //图片
//        String img = request.getParameter("img").trim();
        Community community = new Community();
        community.setName(name);
        community.setType(Integer.parseInt(type));
        community.setContent(content);
        if (Integer.parseInt(type) == 0 || Integer.parseInt(type) == 1) {
            community.setUrl(path);
            community.setImg(null);
        } else if (Integer.parseInt(type) == 2) {
            community.setUrl(null);
            community.setImg(path);
        }
        boolean flag = communityService.insert(community);
        if (flag) {
            //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
//            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "添加失败");
//        return jsonObject;
        //todo 存数据库xxxxxxx
        System.out.println(path);
        return new FileUploadDto();

    }


    /*上传图片*/
    @PostMapping(value = "/uploadImg")
    public Object uploadImg(@RequestParam("file") MultipartFile mpFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "图片上传失败");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "img" + System.getProperty("file.separator") + "communityPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/img/communityPic/" + fileName;
        try {
            mpFile.transferTo(dest);
            Community community = new Community();
            community.setId(id);
            community.setImg(storeSongPath);
            boolean flag = communityService.uploadImg(community);
            if (flag) {
                jsonObject.put(Consts.CODE, 1);
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("img", storeSongPath);
                System.out.println(storeSongPath);
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

    /*上传url*/
    @PostMapping(value = "/uploadUrl")
    public Object uploadUrl(@RequestParam("file") MultipartFile mpFile, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        if (mpFile.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "图片上传失败");
            return jsonObject;
        }
        Community com = communityService.selectById(id);
        int type = com.getType();
        String folder = null;
        if (type == 0) {
            folder = "song";
        } else if (type == 1) {
            folder = "video";
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + folder;
        //如果文件路径不存在新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        //存储到数据库里的相对文件地址
        String storeSongPath = "/" + folder + "/" + fileName;
        try {
            mpFile.transferTo(dest);
            Community community = new Community();
            community.setId(id);
            community.setUrl(storeSongPath);
            boolean flag = communityService.uploadUrl(community);
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


    /*删除*/
    @DeleteMapping("/delete")
    public Object deleteCommunity(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        Community community = communityService.selectById(Integer.parseInt(id));
        int type = community.getType();
        if (type == 3) {
            return communityService.deleteById(Integer.parseInt(id));
        } else if (type == 0 || type == 1) {
            String url = community.getUrl();
            System.out.println(url);
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
            try {
                communityService.deleteById(Integer.parseInt(id));
                File file = new File(filePath + url);
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
        } else {
            String img = community.getImg();
            System.out.println(img);
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator");
            try {
                communityService.deleteById(Integer.parseInt(id));
                File file = new File(filePath + img);
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
        return communityService.selectById(Integer.parseInt(id));
    }

    /**
     * 查询所有动态
     *
     * @return
     */
    @GetMapping(value = "/allCommunity")
    public List allCommunity() {
        List<Object> list1 = new ArrayList<>();

        for (Community m : communityService.allCommunity()) {
            for (Consumer e : consumerService.consumerByName(m.getName())) {
                m.setUserpic(e.getUserpic());
                for (CommunityUp u : communityUpService.getLiked(e.getId())) {
                    System.out.println("状态"+u.getLikeStatus());
//                    m.setLikeStatus(u.getLikeStatus());
                }
            }
            if (m.getUrl() != null && m.getType() == 0) {
                String sname = m.getUrl().substring(m.getUrl().lastIndexOf('/'));
                String song_name = sname.substring(14, sname.lastIndexOf(".")).replace(" ", "");
                System.out.println(song_name);
                for (Song s : songService.songOfSongName(song_name)) {
                    m.setSongpic(s.getSong_pic());
                    m.setLyric(s.getLyric());
                }
            }

            list1.add(m);
        }

        return list1;
    }

    /**
     * 获得指定用户id的动态列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/communityOfUsername")
    public Object communityOfUsername(HttpServletRequest request) {
        String name = request.getParameter("name");
        return communityService.selectByUsername(name);
    }

    /**
     * 获得指定时间的动态列表
     *
     * @param request
     * @return
     */
//    @GetMapping(value = "/communityOfTime")
//    public Object communityOfTime(HttpServletRequest request) {
//        Date time = request.getParameter("create_time");
//        return communityService.selectByTime(Date.from(time));
//    }

    /**
     * 点赞
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/like")
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id".trim()); //id
        String up = request.getParameter("up").trim(); //点赞
        //保存到评论对象中
        Community community = new Community();
        community.setId(Integer.parseInt(id));
        community.setUp(Integer.parseInt(up));
        //判断是否修改成功
        boolean flag = communityService.updateById(community);
        if (flag) {
            //修改成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }
}
