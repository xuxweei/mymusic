package com.xuwei.music.service.impl;

import com.xuwei.music.dao.AdminDao;
import com.xuwei.music.entity.Admin;
import com.xuwei.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 管理员service实现类
* */
@Service
public class AdminServiceImpl implements AdminService {

    //自动注入
    @Autowired
    private AdminDao adminDao;

    /**
    * 验证账号密码是否正确
    *
    * @param username
    * @param password
    * */
    @Override
    public boolean verifyUser(String username, String password) {
        int result = adminDao.verifyUser(username,password);
        if (result>0){
            return true;
        }
        return false;
    }
}
