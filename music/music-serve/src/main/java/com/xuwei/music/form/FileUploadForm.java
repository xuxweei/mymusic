package com.xuwei.music.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileUploadForm
 * @Description TODO
 * @Author 许薇
 * @Date 2021/4/8 23:25
 * @Version 1.0
 */
@Data
public class FileUploadForm {

    private MultipartFile file;

    private Integer type;

    private String name;

    private String content;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
