package com.example.demo.category.controller;

import com.example.demo.category.mapper.CategoryMapper;
import com.example.demo.category.pojo.Category;
import com.example.demo.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/get")
    public String get(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("catelist",categoryList);
        return "list";
    }
}
