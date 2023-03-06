package com.example.dao.pojo;

import lombok.Data;

@Data
public class Comment {
    private long id;
    private String content;
    private long createDate;
    private long article_id;
    private long parent_id;
    private long to_uid;
    private long author_id;
    private int level;
}
