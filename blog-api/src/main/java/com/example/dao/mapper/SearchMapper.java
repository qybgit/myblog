package com.example.dao.mapper;

import com.example.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {
    @Select("select * from my_article where title like '%${text}%'")
    List<Article> search(String text);
}
