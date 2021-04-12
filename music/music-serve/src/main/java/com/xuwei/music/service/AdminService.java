package com.xuwei.music.service;

import com.xuwei.music.entity.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 管理员service接口
* */
public interface AdminService {
    //验证账号密码是否正确
    public boolean verifyUser(String username,String password);
}
