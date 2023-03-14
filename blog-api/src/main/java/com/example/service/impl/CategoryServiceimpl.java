package com.example.service.impl;
import com.example.dao.mapper.CategoryMapper;
import com.example.dao.pojo.Article;
import com.example.dao.pojo.Category;
import com.example.service.CategoryService;
import com.example.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    /**
     * 通过id查分类
     * @param category_id
     * @return
     */
    @Override
    public Category findById(int category_id) {
        Category category=categoryMapper.findById(category_id);
        return category;
    }

    /**
     * all分类
     * @return
     */
    @Override
    public Result findAll() {
        List<Category> categories=categoryMapper.selectAll();
        return Result.success(categories) ;
    }


    public Article selectArticle(int id) {
        Article article=categoryMapper.selectArticle(id);
        return article;
    }
}
