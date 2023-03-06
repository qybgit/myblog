package com.example.controller;

import com.example.service.LoginService;
import com.example.vo.Result;
import com.example.vo.params.Account;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    LoginService loginService;
    @RequestMapping("login")
    public Result login(@RequestBody Account account){

        return loginService.login(account) ;
    }
}
