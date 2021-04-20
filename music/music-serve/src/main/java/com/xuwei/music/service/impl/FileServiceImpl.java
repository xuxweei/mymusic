package com.xuwei.music.service.impl;

import com.xuwei.music.utils.DirTypes;
import com.xuwei.music.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName FileServiceImpl
 * @Description TODO
 * @Author 许薇
 * @Date 2021/4/8 22:57
 * @Version 1.0
 */
@Component
@Slf4j
public class FileServiceImpl implements FileService {


    @Value("${file-store}")
    private String fileStore;
    @Value("${file-base-request}")
    private String baseRquest;


    //初始化目录文件夹
    @Autowired
    private void init() {
        File fIle = new File(fileStore);
        try {
            if (!fIle.exists()) {
                fIle.mkdir();
            }
            System.out.println("初始化文件夹");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String writeFIle(byte[] content) {
        return null;
    }

    //写文件
    @Override
    public String writeFile(MultipartFile file, Integer dir) {
        String fileNmae = file.getOriginalFilename();
        String dirPath = null;
        String t = "";
        if (dir == DirTypes.SONG) {
            t = "song";
            dirPath = fileStore + "/" + "song";
        } else if (dir == DirTypes.VIDEO) {
            t = "video";
            dirPath = fileStore + "/" + "video";
        } else if (dir == DirTypes.IMG) {
            t = "img/communityPic";
            dirPath = fileStore + "/" + "img" + "/" + "communityPic";
        } else {
            return null;
        }
        if (null != dirPath) {
            File dirFIle = new File(dirPath);
            try {
                if (!dirFIle.exists())
                    dirFIle.mkdir();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        File targetFile = new File(dirPath + "/" + System.currentTimeMillis() + fileNmae);
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(targetFile);
            out.write(file.getBytes());
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return baseRquest + "/" + t + "/" + System.currentTimeMillis() + fileNmae;
    }

}
