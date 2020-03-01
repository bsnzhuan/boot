package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        //string类型
        /*redisTemplate.opsForValue().set("test_redis_one","111");
        redisTemplate.delete("test_redis_one");
        stringRedisTemplate.opsForValue().set("test_redis_two","数据存取测试",10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("test_redis_two"));*/
        //list类型
        /*redisTemplate.opsForList().leftPush("test_list","list_one"); leftPop取出左侧第一个元素，并移除
        redisTemplate.opsForList().rightPush("test_list","list_one"); rightPop*/
        //Hash类型
        redisTemplate.opsForHash().put("hashkey","first","firstvalue");
        //是否存在指定map键
        Assert.assertTrue(redisTemplate.opsForHash().hasKey("hashkey","first"));
        Object object = redisTemplate.opsForHash().get("hashkey","first");
        Assert.assertEquals("firstvalue",object);//redistemplate.delete(key)
    }
    @Test
    public void settest(){
        //Set类型
        redisTemplate.opsForSet().add("settest","1","2","3");
        long size = redisTemplate.opsForSet().size("settest");
        Assert.assertEquals(3L,size);
        Set<String> set = redisTemplate.opsForSet().members("settest");
        System.out.println(set);

    }
}
