package com.example.service;

import com.example.vo.CommentVo;
import com.example.vo.Result;
import com.example.vo.params.CommentParam;

public interface CommentService {
    CommentVo findCommentByArticleId(long id);

    Result addComment(CommentParam commentParam);

}
