package com.xuwei.music.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtils {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 读取缓存
     */
    public String get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key,String value){
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key, value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
