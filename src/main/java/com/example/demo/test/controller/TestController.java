package com.example.demo.test.controller;

import com.example.demo.anno.ControllerWebLog;
import com.example.demo.test.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zhihu")
public class TestController {

    @Autowired
    private ConfigInfo configInfo;

    @GetMapping("/{str}")
    @ResponseBody
    public String getString(@PathVariable String str) throws Exception {
        //System.out.println("进入1 "+ configInfo.getName()+"9");
        //throw new Exception("11");
        return str+"get";
    }

    @PostMapping("/{str}")
    @ResponseBody
    public String postString(@PathVariable String str){
        return str+"post";
    }

    //aop aspect切面测试
    @PostMapping("post-test")
    @ResponseBody
    @ControllerWebLog(name="test-method",intoDb = true)
    public String hello(@RequestParam String name){
        return name;
    }
}
