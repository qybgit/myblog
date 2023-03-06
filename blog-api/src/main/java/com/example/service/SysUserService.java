package com.example.service;

import com.example.dao.pojo.SysUser;
import com.example.vo.SysUserVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface SysUserService {

    SysUserVo selectUserById(long to_uid);

    SysUser checkToken(String token);
}
