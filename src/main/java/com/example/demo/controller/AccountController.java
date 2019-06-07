package com.example.demo.controller;


import com.example.demo.beans.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author handaquan
 * @since 2019-06-07
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/list")
    public String ge(){
       List<Account> results = accountService.list();
        results.forEach(Account -> System.out.println(Account.toString()));
        return "OK";
    }

}

