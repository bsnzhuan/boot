package com.example.demo.test.controller;

import com.example.demo.test.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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
}
