package com.example.service;

import com.example.vo.Result;

public interface TagService {
    Result selectAll();


    Result addTag(String nickname);
}
