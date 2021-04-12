package com.xuwei.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 热更新、热加载
 * 1.Ctrl+Shift+A --> 搜索registry，找到registry...，找到compiler.automake.allow.when.app.running,勾选
 * 2.执行 Ctrl+F9 进行热加载
 *
 *  */
@SpringBootApplication
@MapperScan("com.xuwei.music.dao")
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
