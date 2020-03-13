package com.villageroad.storage.db.shijia.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.villageroad.storage.db.shijia.dao.SellerInfoDao;
import com.villageroad.storage.db.shijia.entity.SellerInfo;
import com.villageroad.storage.db.shijia.mapper.SellerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houshengbin
 */
@Service
public class SellerInfoDaoImpl extends ServiceImpl<SellerInfoMapper, SellerInfo> implements SellerInfoDao {

    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Override
    public SellerInfo findByUsername(String username) {
        LambdaQueryWrapper<SellerInfo> query = Wrappers.lambdaQuery();
        query.eq(SellerInfo::getUsername, username);
        query.last("limit 1");
        return sellerInfoMapper.selectOne(query);
    }
}
