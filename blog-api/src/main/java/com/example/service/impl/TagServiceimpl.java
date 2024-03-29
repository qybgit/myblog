package com.example.service.impl;

import com.example.dao.mapper.TagMapper;
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
        for(Tag tag:tagList){
            int count=findCount(tag);
            tag.setCount(count);
        }
        return Result.success(tagList);
    }
//评论数量
    private int findCount(Tag tag) {

        int count=tagMapper.findCount(tag.getId());
        return  count;
    }

    /**
     * 添加标签
     * @param nickname
     * @return
     */
    @Override
    public Result addTag(String nickname) {
        if (!addTAG(nickname))
            return Result.fail(400,"添加失败",null);
        return Result.success("添加成功");
    }

    /**
     * 查找文章标签
     * @param id
     * @return
     */
    @Override
    public List<Tag> selectByArticleId(Long id) {
        List<Tag> tags=tagMapper.selectArticleById(id);
        return tags;
    }

    @Override
    public Result selectCount() {
        int count=tagMapper.findTagCount();
        return Result.success(count);
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
