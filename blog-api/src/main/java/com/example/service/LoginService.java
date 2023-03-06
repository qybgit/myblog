package com.example.service;

import com.example.vo.Result;
import com.example.vo.params.Account;

public interface LoginService {
    Result register(Account account);

    Result login(Account account);
}
