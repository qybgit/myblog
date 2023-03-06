package com.example.dao.mapper;

import com.example.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from my_comment where article_id=#{id} and level=1")
    Comment findCommentByArticleId(long id);

    @Select("select * from my_comment where parent_id=#{id} and article_id=#{article_id}")
    List<Comment> findCommentByparentId(long id,long article_id);
}
