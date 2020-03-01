package com.example.demo.category.service.impl;

import com.example.demo.category.mapper.CategoryMapper;
import com.example.demo.category.pojo.Category;
import com.example.demo.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public int save(Category category) {
        return categoryMapper.save(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.get(id);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public Map test(int num) {
        Map map = new HashMap();
        map.put("number","1");
        categoryMapper.test(map);
        System.out.println(map.toString());
        return map;
    }

    @Override
    public int secondTest(int num) {
        return categoryMapper.secondTest(num);
    }
}
