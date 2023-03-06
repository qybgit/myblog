package com.example.service.impl;

import com.example.dao.mapper.TagMapper;
import com.example.dao.pojo.Article;
import com.example.dao.pojo.Tag;
import com.example.service.TagService;
import com.example.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceimpl implements TagService {
    @Resource
    TagMapper tagMapper;
    @Override
    public Result selectAll() {
        List<Tag> tagList=tagMapper.selectAll();
        return Result.success(tagList);
    }

    @Override
    public Result addTag(String nickname) {
        if (!addTAG(nickname))
            return Result.fail(400,"添加失败",null);
        return Result.success("添加成功");
    }

    @Rollback
    @Transactional( rollbackFor = Exception.class)
    public boolean addTAG(String nickname) {
        try {
            tagMapper.insertTag(nickname);
        }catch (Exception e){
            throw  e;
        }
        return true;
    }

    public List<Long> selectAListId(Long id){
        List<Long> aListId=tagMapper.selectAListId(id);
        return aListId;
    }


}
