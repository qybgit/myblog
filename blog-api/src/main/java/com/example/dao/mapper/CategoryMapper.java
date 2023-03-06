package com.example.dao.mapper;

import com.example.dao.pojo.Article;
import com.example.dao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from my_category where id=#{category_id}")
    Category findById(int category_id);

    @Select("select * from my_article where category_id=#{id}")
    Article selectArticle(int id);

    @Select("select * from my_category")
    List<Category> selectAll();

}
