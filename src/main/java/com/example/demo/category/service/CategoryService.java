package com.example.demo.category.service;

import com.example.demo.category.pojo.Category;

import java.util.List;

public interface CategoryService{
    List<Category> findAll();

    int save(Category category);

    void delete(int id);

    Category get(int id);

    int update(Category category);
}
