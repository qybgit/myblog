package com.example.dao.pojo;

import lombok.Data;

@Data
public class Tag {
    private int id;
    private String tag_Name;
    private int count;//评论数量
}
