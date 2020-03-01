package com.example.demo.category.controller;

import com.example.demo.category.pojo.Category;
import com.example.demo.category.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/listCategory")
    public String get(Model model,
                      @RequestParam(value = "start", defaultValue = "0") int start,
                      @RequestParam(value = "size", defaultValue = "5") int size) throws  Exception{
        PageHelper.startPage(start,size,"id desc");
        List<Category> categoryList = categoryService.findAll();
        PageInfo<Category> page = new PageInfo<>(categoryList);
        model.addAttribute("page",page);
        return "listcategory";
    }

    @RequestMapping("/editCategory")
    public String listCategory(int id,Model m) throws Exception {
        Category c= categoryService.get(id);
        m.addAttribute("c", c);
        return "editCategory";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
        categoryService.update(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/addCategory")
    public String listCategory(Category c) throws Exception {
        categoryService.save(c);
        return "redirect:listCategory";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
        categoryService.delete(c.getId());
        return "redirect:listCategory";
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return String.valueOf(categoryService.test(1));
    }

    @RequestMapping("/secondTest")
    @ResponseBody
    public String secondTest(){
        return String.valueOf(categoryService.secondTest(1));
    }
}
