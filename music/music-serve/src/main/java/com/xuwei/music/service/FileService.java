package com.xuwei.music.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author 许薇
 * @Date 2021/4/8 22:56
 * @Version 1.0
 */
public interface FileService {

    String writeFIle(byte[] content);

    String writeFile(MultipartFile file,Integer dirType);

}
