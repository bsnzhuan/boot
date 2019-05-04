package com.example.demo.category.mapper;

import com.example.demo.category.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper{
    @Select("select * from category ")
    List<Category> findAll();

    @Insert(" insert into category ( name ) values (#{name}) ")
    public int save(Category category);

    @Delete(" delete from category where id= #{id} ")
    public void delete(int id);

    @Select("select * from category where id= #{id} ")
    public Category get(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    public int update(Category category);
}
