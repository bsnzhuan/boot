package com.example.demo.aspect;

import com.alibaba.fastjson.JSON;
import com.example.demo.anno.ControllerWebLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * aop切面
 */
@Aspect
@Component
@Order(100) //切面增强执行顺序
public class TestAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(TestAspect.class);

    private static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal();

    @Pointcut("execution(* com.example.demo.test.controller..*(..))")
    public void test(){}

    @Before(value = "@annotation(controllerWebLog)")
    public void doBefore(JoinPoint joinPoint
            , ControllerWebLog controllerWebLog){
        //开始时间
        long startTime = System.currentTimeMillis();
        Map<String,Object> threadInfo = new HashMap();
        threadInfo.put("startTime",startTime);
        //方法请求参数
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length > 0){
            for (Object arg : args){
               requestStr.append(arg.toString());
            }
        }
        threadInfo.put("requestParams",requestStr.toString());
        threadLocal.set(threadInfo);
        logger.info("{}接口开始调用:requestData={}", controllerWebLog.name(), threadInfo.get("requestParams"));
    }

    @AfterReturning(value = "@annotation(controllerWebLog)", returning = "res")
    public void doAfterReturning(ControllerWebLog controllerWebLog,Object res){
        Map<String,Object> threadInfo = threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long)threadInfo.getOrDefault("startTime",System.currentTimeMillis());
        if(controllerWebLog.intoDb()){
            redisTemplate.opsForValue().set(UUID.randomUUID().toString(), JSON.toJSONString(res));
        }
        threadLocal.remove();
        logger.info("{}接口结束调用:耗时={}ms,result={}", controllerWebLog.name(),
                takeTime, res);
    }
}
