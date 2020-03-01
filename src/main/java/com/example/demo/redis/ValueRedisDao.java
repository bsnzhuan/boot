package com.example.demo.redis;

import com.example.demo.tools.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ValueRedisDao {
    @Autowired
    public RedisBaseDao redisBaseDao;

    private String getKey(){
        return "param";
    }

    public void save(String param){
        this.redisBaseDao.addValue(this.getKey(),param);
    }

    public String getParam(){
        return this.redisBaseDao.getValue(this.getKey());
    }
}
