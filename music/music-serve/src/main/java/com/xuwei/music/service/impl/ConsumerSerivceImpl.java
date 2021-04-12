package com.xuwei.music.service.impl;

import com.xuwei.music.dao.ConsumerDao;
import com.xuwei.music.entity.Consumer;
import com.xuwei.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前端用户service实现类
 */
@Service
public class ConsumerSerivceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return consumerDao.deleteByPrimaryKey(id) > 0 ? true : false;
    }

    /**
     * 添加(其他元素全具备)
     *
     * @param record
     * @return
     */
    @Override
    public boolean insert(Consumer record) {
        return consumerDao.insert(record) > 0 ? true : false;
    }

    /**
     * 添加(选择性)
     *
     * @param record
     * @return
     */
    @Override
    public boolean insertConsumer(Consumer record) {
        return consumerDao.insertConsumer(record) > 0 ? true : false;
    }

    /**
     * 根据id查询整个对象
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerDao.selectByPrimaryKey(id);
    }

    /**
     * 根据id全部更新
     *
     * @param record
     * @return
     */
    @Override
    public boolean updateByPrimaryKey(Consumer record) {
        return consumerDao.updateByPrimaryKey(record) > 0 ? true : false;
    }

    /**
     * 更新用户信息
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerDao.updateUserMsg(consumer) > 0 ? true : false;
    }

    /**
     * 更新用户头像
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean updateUserAvator(Consumer consumer) {
        return consumerDao.updateUserAvator(consumer) > 0 ? true : false;
    }

    /**
     * 验证密码 根据用户名和密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerDao.verifyPassword(username, password) > 0 ? true : false;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerDao.allConsumer();
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public List<Consumer> consumerOfId(Integer id) {
        return consumerDao.consumerOfId(id);
    }

    /**
     * 登录状态
     *
     * @param username
     * @return
     */
    @Override
    public List<Consumer> loginStatus(String username) {
        return consumerDao.loginStatus(username);
    }

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    @Override
    public List<Consumer> consumerByName(String username) {
        return consumerDao.consumerByName(username);
    }

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    @Override
    public  Consumer  consumerByUserName(String username) {
        return consumerDao.consumerByUserName(username);
    }

}
