package com.xuwei.music.service;

import com.xuwei.music.entity.Consumer;

import java.util.List;

/**
 * 前端用户service接口
 */
public interface ConsumerService {
    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean deleteByPrimaryKey(Integer id);

    /**
     * 添加(其他元素全具备)
     *
     * @param record
     * @return
     */
    boolean insert(Consumer record);

    /**
     * 添加(选择性)
     *
     * @param record
     * @return
     */
    boolean insertConsumer(Consumer record);

    /**
     * 根据id查询整个对象
     *
     * @param id
     * @return
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 根据id全部更新
     *
     * @param record
     * @return
     */
    boolean updateByPrimaryKey(Consumer record);

    /**
     * 验证密码 根据用户名和密码
     *
     * @param username
     * @param password
     * @return
     */
    boolean verifyPassword(String username, String password);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<Consumer> allConsumer();

    /**
     * 根据指定id查询用户
     *
     * @param id
     * @return
     */
    List<Consumer> consumerOfId(Integer id);


    /**
     * 登录状态
     *
     * @param username
     * @return
     */
    List<Consumer> loginStatus(String username);

    /**
     * 根据账号查询
     *
     * @param username
     * @return
     */
    List<Consumer> consumerByName(String username);

    Consumer consumerByUserName(String username);


    /**
     * 更新用户信息
     *
     * @param consumer
     * @return
     */
    boolean updateUserMsg(Consumer consumer);

    /**
     * 更新用户头像
     *
     * @param consumer
     * @return
     */
    boolean updateUserAvator(Consumer consumer);

}
