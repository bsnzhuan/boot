package com.example.demo.tools;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * redis工具类
 */
@Repository
public class RedisBaseDao {
    @Resource(name="redisTemplate")
    private ValueOperations<String,String> valueOperations;

    public void addValue(String key,String value){
        valueOperations.set(key,value);
    }

    public String getValue(String key){
        return valueOperations.get(key);
    }
}
