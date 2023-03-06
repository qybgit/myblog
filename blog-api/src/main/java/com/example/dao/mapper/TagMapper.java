package com.example.dao.mapper;

import com.example.dao.pojo.Article;
import com.example.dao.pojo.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {
    @Select("select * from my_tag")
    List<Tag> selectAll();

    @Select("select * from Article")
    List<Integer> selectArticleById(int id);

    @Select("select article_id from my_tag_article where Tag_id=#{id}" )
    List<Long> selectAListId(Long id);

    @Insert("insert into my_tag(tag_name) values(#{nickname})")
    void insertTag(String nickname);
}
