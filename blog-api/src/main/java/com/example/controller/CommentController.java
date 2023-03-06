package com.example.controller;

import com.example.service.CommentService;
import com.example.vo.Result;
import com.example.vo.params.CommentParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    CommentService commentService;
    @RequestMapping("add")
    public Result add(@RequestBody CommentParam commentParam) {

        return commentService.addComment(commentParam);
    }
}
