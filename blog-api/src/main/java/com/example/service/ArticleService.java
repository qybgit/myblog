package com.example.service;

import com.example.vo.Result;
import com.example.vo.params.ArticleParam;
import com.example.vo.params.PageParams;
import org.springframework.stereotype.Service;


public interface ArticleService {

    Result findAllArticle(PageParams params);

    Result selectArticleByTagId(Long id);

    Result selectArticle(int id);

    Result findArticle(Long id);

    Result addArticle(ArticleParam articleParam);

    Result articleCount();
}
