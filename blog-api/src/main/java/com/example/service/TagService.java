package com.example.service;

import com.example.dao.pojo.Tag;
import com.example.vo.Result;

import java.util.List;

public interface TagService {
    Result selectAll();


    Result addTag(String nickname);

    List<Tag> selectByArticleId(Long id);
}
