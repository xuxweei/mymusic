package com.xuwei.music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
* 解决跨域问题
* */
//Springboot启动时自动加载方法
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${file-store}")
    private String fileStore ;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        /*
        * addMapping:对所有目录实现跨域访问
        * allowedOrigins:对所有网站实现跨域访问
        * allowedMethods:对所有方法实现跨域访问
        * allowCredentials:访问时是否需要验证
        * */
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }


    //配置静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static-files/**")
                .addResourceLocations("file:"+(fileStore.endsWith("/")?fileStore:fileStore+"/"));
    }
}
