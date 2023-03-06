package com.example.vo;

import com.example.dao.pojo.Category;
import lombok.Data;

@Data
public class ArticleVo {
    private  String id;
    private String title;
    private String createDate;
    private String updateDate;
    private String summary;
    private Category category;
    private ArticleBodyVo articleBodyVo;
    private CommentVo commentVo;
    private int author_id;
//    private int body_id;
}
