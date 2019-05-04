package com.example.demo.test.controller;

import com.example.demo.test.config.ConfigInfo;
import com.example.demo.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@Controller
public class HelloController {

    @Value("${boot.name}")
    private String name;

    @Value("${boot.location}")
    private String location;

    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping("/test/hello")
    private @ResponseBody String hello() throws Exception{
        /*if(true) {
            throw new Exception("error");
        }*/
        return configInfo.getName()+"     "+configInfo.getLocation()+" 22225622  "+name+" 111441333   "+location;
    }
    @RequestMapping("/test/index")
    public String index(Model model){
        model.addAttribute("bool",true);
        model.addAttribute("bo",false);
        model.addAttribute("text","这是一段话");
        model.addAttribute("text1","这是两段话");
        return "index";
    }

    @RequestMapping("/test/list")
    public String list(Model mode){
        List list = new ArrayList();
        User user = new User(1,"name",10);
        User user1 = new User(2,"name",10);
        User user2 = new User(3,"name",10);
        User user3 = new User(4,"name",10);
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        mode.addAttribute("now",new Date());
        mode.addAttribute("list",list);
        return "list";
    }
}
