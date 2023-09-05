package com.villageroad.service;

import com.villageroad.storage.db.shijia.entity.Account;

/**
 * @author houshengbin
 */
public interface AccountService {
    /**
     * 根据用户名和密码获取账户
     *
     * @param userName 用户名
     * @param password 密码
     * @return 账户
     */
    Account getAccountByUserNameAndPassword(String userName, String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 账户
     */
    Account getUserInfo(String userName);

    /**
     * 创建账户
     * @param account
     * @return
     */
    Account createAccount(Account account);
}
