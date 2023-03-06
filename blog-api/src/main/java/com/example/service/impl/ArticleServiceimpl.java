package com.example.service.impl;

import com.example.dao.mapper.ArticleMapper;
import com.example.dao.pojo.Article;
import com.example.dao.pojo.ArticleBody;
import com.example.dao.pojo.Category;
import com.example.dao.pojo.SysUser;
import com.example.service.ArticleService;
import com.example.service.CategoryService;
import com.example.service.CommentService;
import com.example.util.UserThreadLocal;
import com.example.vo.ArticleBodyVo;
import com.example.vo.ArticleVo;
import com.example.vo.CommentVo;
import com.example.vo.Result;
import com.example.vo.params.ArticleBodyParam;
import com.example.vo.params.ArticleParam;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceimpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    CategoryServiceimpl categoryService;
    @Resource
    CommentService commentService;
    @Resource
    TagServiceimpl tagService;

    /**
     * 所有文章
     *
     * @return
     */
    @Override
    public Result findAllArticle() {
        List<Article> articleList = articleMapper.findAllArticle();
        List<ArticleVo> articleVoList = copyList(articleList, false, false);
        return Result.success(articleVoList);
    }

    /**
     * 标签分类文章
     * @param id
     * @return
     */
    @Override
    public Result selectArticleByTagId(Long id) {
        List<Long> articleIdList = tagService.selectAListId(id);

        if (articleIdList == null || articleIdList.size() == 0) {
            return Result.success("标签不存在");
        }

        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Long l : articleIdList) {
            articleVoList.add(selectArticleById(l));
        }

        return Result.success(articleVoList);
    }

    /**
     * 分类id查找
     * @param id
     * @return
     */
    @Override
    public Result selectArticle(int id) {
        return Result.success(copy(categoryService.selectArticle(id), false, false));
    }

    /**
     * 文章详情
     * @param id
     * @return
     */
    @Override
    public Result findArticle(Long id) {
        Article article = articleMapper.selectArticleById(id);
        ArticleVo articleVo = copy(article, true, true);

        return Result.success(articleVo);
    }

    /**
     * 添加文章
     * @param articleParam
     * @return
     */
    @Override
    public Result addArticle(ArticleParam articleParam) {
        SysUser sysUser= UserThreadLocal.get();
        Article article = new Article();
        BeanUtils.copyProperties(articleParam, article);
        article.setCreateDate(System.currentTimeMillis());
        article.setUpdateDate(System.currentTimeMillis());
        article.setAuthor_id(sysUser.getId());
        Long id = publish(article);
        if (null == id) {
            return Result.fail(400, "发布失败", null);
        }
        Integer body_id = publishBody(articleParam.getBody(),id);
        if (body_id == null) {
            return Result.fail(400, "发布失败", null);
        }
        if (!updateArticle(body_id,id)){
            return Result.fail(400,"发布失败",null);
        }


        return Result.success("发布成功");
    }

    /**
     * 更新文章
     * @param body_id
     * @return
     */
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticle(Integer body_id,long id) {
        try{
            articleMapper.update(body_id,id);
        }catch (Exception e){
            throw e;
        }
        return true;
    }

    /**
     * 插入body
     * @param body
     * @return
     */
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    public Integer publishBody(ArticleBodyParam body,long id) {
        ArticleBody articleBody=new ArticleBody();
        BeanUtils.copyProperties(body,articleBody);
        articleBody.setArticle_id(id);
        try {
            articleMapper.insertBody(articleBody);
        }catch (Exception e){
            throw e;
        }
        return articleBody.getId();
    }

    /**
     * 插入文章
     * @param article
     * @return
     */
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    public Long publish(Article article) {
        try {

             articleMapper.insertArticle(article);

        } catch (Exception e) {
            throw e;
        }
        return article.getId();
    }

    public ArticleVo selectArticleById(Long l) {
        Article article = articleMapper.selectById(l);

        return copy(article, false, false);
    }

    //复制属性
    public List<ArticleVo> copyList(List<Article> articleList, boolean isBody, boolean isComment) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleVo articleVo = copy(article, isBody, isComment);
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }

    public ArticleVo copy(Article article, boolean isBody, boolean isComment) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-mm-dd hh-mm"));
        articleVo.setUpdateDate(new DateTime(article.getUpdateDate()).toString("yyyy-mm-dd hh-mm"));
        Category category = categoryService.findById(article.getCategory_id());
        articleVo.setCategory(category);
        if (isBody) {
            ArticleBody articleBody = articleMapper.findArticleBody(article.getBody_id());
            ArticleBodyVo articleBodyVo = new ArticleBodyVo();
            BeanUtils.copyProperties(articleBody, articleBodyVo);
            articleVo.setArticleBodyVo(articleBodyVo);
        }
        if (isComment) {
            CommentVo commentVo = commentService.findCommentByArticleId(article.getId());
            if (commentVo == null) {
                articleVo.setCommentVo(null);
            } else {
                articleVo.setCommentVo(commentVo);
            }
        }
        return articleVo;
    }
}
