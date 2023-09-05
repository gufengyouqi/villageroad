package com.villageroad.storage.db.shijia.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.villageroad.storage.db.shijia.dao.AccountDao;
import com.villageroad.storage.db.shijia.entity.Account;
import com.villageroad.storage.db.shijia.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houshengbin
 */
@Service
public class AccountDaoImpl extends ServiceImpl<AccountMapper, Account> implements AccountDao {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccountByUserName(String userName) {
        LambdaQueryWrapper<Account> query = Wrappers.lambdaQuery();
        query.eq(Account::getUsername, userName);
        return accountMapper.selectOne(query);
    }
}
