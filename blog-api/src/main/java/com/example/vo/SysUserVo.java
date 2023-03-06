package com.example.vo;

import lombok.Data;

@Data
public class SysUserVo {
    private long id;
    private String account;
    private int admin;
    private String avatar;
    private String nickName;
}
