package com.example.controller;

import com.example.service.CommentService;
import com.example.vo.Result;
import com.example.vo.params.CommentParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("comment")
@CrossOrigin(origins = "*")
public class CommentController {
    @Resource
    CommentService commentService;

    @RequestMapping("add")
    @CrossOrigin(origins = "*")
    public Result add(@RequestBody CommentParam commentParam) {

        return commentService.addComment(commentParam);
    }
}
