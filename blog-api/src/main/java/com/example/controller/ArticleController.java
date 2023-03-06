package com.example.controller;

import com.example.service.ArticleService;
import com.example.vo.Result;
import com.example.vo.params.ArticleParam;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Resource
    ArticleService articleService;

    /**
     * 所有文章
     */
    @RequestMapping("all")
    public Result all() {
        return articleService.findAllArticle();
    }

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    public Result article(@PathVariable("id") Long id) {
        return articleService.findArticle(id);
    }

    /**
     * 增加文章
     */
    @PostMapping("add")
    public Result addArticle(@RequestBody ArticleParam articleParam) {

        return articleService.addArticle(articleParam);

    }

}
