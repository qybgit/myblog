package com.example.service;

import com.example.vo.CommentVo;
import com.example.vo.Result;
import com.example.vo.params.CommentParam;

import java.util.List;

public interface CommentService {
    List<CommentVo> findCommentByArticleId(long id);

    Result addComment(CommentParam commentParam);

}
