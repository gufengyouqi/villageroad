package com.villageroad.service.impl;

import com.villageroad.exception.BizException;
import com.villageroad.model.BizError;
import com.villageroad.service.AccountService;
import com.villageroad.storage.db.shijia.dao.AccountDao;
import com.villageroad.storage.db.shijia.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author houshengbin
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account getAccountByUserNameAndPassword(String userName, String password) {
        Account account = accountDao.getAccountByUserName(userName);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                return account;
            } else {
                log.warn("密码错误 userName:{}", userName);
            }
        }
        throw new BizException(BizError.ERROR_USER_NOT_EXIST);
    }

    @Override
    public Account getUserInfo(String userName) {
        return accountDao.getAccountByUserName(userName);
    }

    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDao.save(account);
        return account;
    }
}
