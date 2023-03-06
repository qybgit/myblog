package com.example;

import com.alibaba.fastjson2.JSON;
import com.example.dao.mapper.CommentMapper;
import com.example.dao.pojo.Comment;
import com.example.dao.pojo.SysUser;
import com.example.dao.pojo.Tag;
import com.example.service.CommentService;
import com.example.service.SysUserService;
import com.example.service.impl.TagServiceimpl;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class BlogTest {
    @Resource
    CommentMapper commentMapper;
    @Resource
    TagServiceimpl tagServiceimpl;
    @Resource
    SysUserService service;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        Date a=new Date();
Long b=a.getTime();

        System.out.println(new DateTime(b).toString("yyyy-mm-dd hh:mm"));
    }
    @Test
    public void test1(){
        Comment comment=commentMapper.findCommentByArticleId(1);
        System.out.println(comment);
    }
    @Test
    public void test2(){
        SysUser sysUser=new SysUser();
        sysUser.setAvatar("e");
        sysUser.setPassword("111111");


        System.out.println(redisTemplate.opsForValue().get("Token_eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjE0MDQ0NDg1ODgxNDYxOTI0MDksImV4cCI6MTY3ODAwODczNn0.2NDJzSrTxOp2IBcSteWh1XmnGsS32ZZnBUJYA_zE5eA"));

    }
}
