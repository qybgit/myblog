package com.example.service.impl;

import com.example.dao.mapper.CommentMapper;
import com.example.dao.pojo.Comment;
import com.example.dao.pojo.SysUser;
import com.example.service.CommentService;
import com.example.service.SysUserService;
import com.example.vo.CommentVo;
import com.example.vo.Result;
import com.example.vo.SysUserVo;
import com.example.vo.params.CommentParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceimpl implements CommentService {
    @Resource
    CommentMapper commentMapper;
    @Resource
    SysUserService sysUserService;

    /**
     * 评论详细
     *
     * @param id
     * @return
     */
    @Override
    public CommentVo findCommentByArticleId(long id) {
        Comment comment = commentMapper.findCommentByArticleId(id);
        if (comment == null)
            return null;
        CommentVo commentVo = copyComment(comment,id);
        return commentVo;

    }

    @Override
    public Result addComment(CommentParam commentParam) {
        if (StringUtils.isBlank(commentParam.getContent()))
            return Result.fail(400,"请重新填写",null);
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentParam,comment);
        return null;
    }

    private CommentVo copyComment(Comment comment,long id) {
        SysUserVo author = sysUserService.selectUserById(comment.getAuthor_id());
        SysUserVo sysUser = sysUserService.selectUserById(comment.getTo_uid());
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setToUser(sysUser);
        commentVo.setAuthor(author);
        List<Comment> commentList = commentMapper.findCommentByparentId(commentVo.getId(),id);
        List<CommentVo> commentVoList=new ArrayList<>();
        if (commentList == null) {
            commentVo.setChildren(null);
            return commentVo;
        } else {
           for (Comment comment1:commentList){
               CommentVo commentVo1=copyComment(comment1,id);
               commentVoList.add(commentVo1);
            }
           commentVo.setChildren(commentVoList);
           return commentVo;
       }


        }
    }
