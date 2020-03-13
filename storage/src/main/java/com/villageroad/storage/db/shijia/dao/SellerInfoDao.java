package com.villageroad.storage.db.shijia.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.villageroad.storage.db.shijia.entity.SellerInfo;

/**
 * @author houshengbin
 */
public interface SellerInfoDao extends IService<SellerInfo> {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    SellerInfo findByUsername(String username);
}
