package com.example.dao.mapper;

import com.example.dao.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from my_comment where article_id=#{id} and level=1")
    List<Comment> findCommentByArticleId(long id);

    @Select("select * from my_comment where parent_id=#{id} and article_id=#{article_id}")
    List<Comment> findCommentByparentId(long id,long article_id);

    @Insert("insert into my_comment(content,createDate,article_id,parent_id,to_uid,level) values(#{content},#{createDate},#{article_id},#{parent_id},#{to_uid},#{level})")
    @Options(useGeneratedKeys = true,keyProperty ="id" )
    Long insertComment(Comment comment);

}
