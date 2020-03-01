package com.example.demo.category.mapper;

import com.example.demo.category.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Update("update category set name=#{name} where id=#{id} ")
    public int update(Category category);

    @Select("call secondProcedure(#{map.number,mode=OUT,jdbcType=INTEGER,javaType=int})")
    @Results({
            @Result(column = "number",property = "number",jdbcType = JdbcType.INTEGER,javaType = int.class)
    })
    @Options(statementType = StatementType.CALLABLE)
    //@Select("call firstProcedure(#{number})")
    public Map test(@Param("map")Map map);

    @Select("call firstProcedure(#{number})")
    public int secondTest(int number);
}
