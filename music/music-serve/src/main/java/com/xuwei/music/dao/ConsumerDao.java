package com.xuwei.music.dao;

import com.xuwei.music.entity.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerDao {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加(其他元素全具备)
     * @param record
     * @return
     */
    int insert(Consumer record);

    /**
     * 添加(选择性)
     * @param record
     * @return
     */
    int insertConsumer(Consumer record);

    /**
     * 根据id查询整个对象
     * @param id
     * @return
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 根据id全部更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Consumer record);

    /**
     * 验证密码 根据用户名和密码
     * @param username
     * @param password
     * @return
     */
    int verifyPassword(String username,String password);

    /**
     * 查询所有用户
     * @return
     */
    List<Consumer> allConsumer();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    List<Consumer> consumerOfId(Integer id);

    /**
     * 登录状态
     * @param username
     * @return
     */
    List<Consumer> loginStatus(String username);

    /**
     * 根据账号查询
     * @param username
     * @return
     */
    List<Consumer> consumerByName(String username);

    Consumer consumerByUserName(String username);


    /**
     * 更新用户信息
     * @param consumer
     * @return
     */
    int updateUserMsg(Consumer consumer);

    /**
     * 更新用户头像
     * @param consumer
     * @return
     */
    int updateUserAvator(Consumer consumer);

}