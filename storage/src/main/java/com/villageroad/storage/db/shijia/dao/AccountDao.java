package com.villageroad.storage.db.shijia.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.villageroad.storage.db.shijia.entity.Account;

/**
 * @author houshengbin
 */
public interface AccountDao extends IService<Account> {
    /**
     * 根据用户名获取账户信息
     * @param userName
     * @return
     */
    Account getAccountByUserName(String userName);
}
