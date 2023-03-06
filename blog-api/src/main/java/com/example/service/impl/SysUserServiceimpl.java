package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.dao.mapper.SysUserMapper;
import com.example.dao.pojo.SysUser;
import com.example.service.SysUserService;
import com.example.util.JwtUtil;
import com.example.vo.SysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SysUserServiceimpl implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    RedisTemplate<String,String> redisTemplate;
    @Override
    public SysUserVo selectUserById(long to_uid) {
        SysUser sysUser=sysUserMapper.selectUserById(to_uid);

        return copyUser(sysUser);
    }

    @Override
    public SysUser checkToken(String token) {
        Map<String,Object> map=JwtUtil.checkToken(token);
        if (map==null)
            return null;
        String o=  redisTemplate.opsForValue().get("Token_"+token);
        SysUser sysUser=JSON.parseObject(o,SysUser.class);


        return sysUser;
    }

    private SysUserVo copyUser(SysUser sysUser) {
        SysUserVo sysUserVo=new SysUserVo();
        BeanUtils.copyProperties(sysUser,sysUserVo);
        return sysUserVo;

    }
}
