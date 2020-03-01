package com.example.demo.category.service;

import com.example.demo.category.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService{
    List<Category> findAll();

    int save(Category category);

    void delete(int id);

    Category get(int id);

    int update(Category category);

    Map test(int num);

    int secondTest(int num);
}
