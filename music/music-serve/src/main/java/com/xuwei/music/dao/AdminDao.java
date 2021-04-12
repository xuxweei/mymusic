package com.xuwei.music.dao;

import com.xuwei.music.entity.Admin;
import org.springframework.stereotype.Repository;

/*
 * 管理员Dao
 * */
@Repository
public interface AdminDao {
    /*验证用户输入的账户密码是否正确*/
    public int verifyUser(String name,String password);
}
