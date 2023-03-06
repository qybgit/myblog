package com.example.service;

import com.example.dao.pojo.Category;
import com.example.vo.Result;

public interface CategoryService {
    Category findById(int category_id);


    Result findAll();
}
