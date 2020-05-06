package com.example.demo.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.anno.JwtPassToken;
import com.example.demo.test.pojo.User;
import com.example.demo.tools.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    static Map<Integer, User> userMap = new HashMap<Integer,User>();

    static{
        User user1 = new User(1,"张三",18,"675");
        userMap.put(1,user1);
        user1 = new User(2,"李四",18,"10163");
        userMap.put(2,user1);
    }

    @JwtPassToken
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value="data") String param){
        System.out.println(param);
        Map map = JSONObject.parseObject(param);
        for (User dataUser : userMap.values()) {
           if(dataUser.getUserCode().equals(map.get("userCode"))){
                Map dataMap = new HashMap();
                dataMap.put("id",map.get("id"));
                dataMap.put("userName",map.get("name"));
                dataMap.put("userCode",map.get("userCode"));
                String token = JwtUtils.createToken(dataMap);
                return token;
            }
        }
        return "";
    }
}
