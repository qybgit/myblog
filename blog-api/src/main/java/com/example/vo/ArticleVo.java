package com.example.vo;

import com.example.dao.pojo.Category;
import com.example.dao.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {
    private  Long id;
    private String title;
    private String createDate;
    private String updateDate;
    private String summary;
    private Category category;
    private ArticleBodyVo articleBodyVo;
    private List<CommentVo> commentVo;
    private long author_id;
    private List<Tag> tags;
//    private int body_id;
}
