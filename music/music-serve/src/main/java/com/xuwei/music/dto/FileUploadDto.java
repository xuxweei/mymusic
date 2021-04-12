package com.xuwei.music.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName FileUploadDto
 * @Description TODO
 * @Author 许薇
 * @Date 2021/4/8 23:46
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class FileUploadDto {

    private String filePath;
}
