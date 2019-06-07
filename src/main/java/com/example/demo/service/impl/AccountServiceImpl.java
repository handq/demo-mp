package com.example.demo.service.impl;

import com.example.demo.beans.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author handaquan
 * @since 2019-06-07
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
