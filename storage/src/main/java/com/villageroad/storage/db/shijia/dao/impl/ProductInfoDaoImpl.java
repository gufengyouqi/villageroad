package com.villageroad.storage.db.shijia.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import com.villageroad.storage.db.shijia.entity.SellerInfo;
import com.villageroad.storage.db.shijia.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author houshengbin
 */
@Service
public class ProductInfoDaoImpl extends ServiceImpl< ProductInfoMapper,  ProductInfo> implements  ProductInfoDao {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findByStatus(Integer status) {
        LambdaQueryWrapper<ProductInfo> query = Wrappers.lambdaQuery();
        query.eq(ProductInfo::getStatus, status);
        return productInfoMapper.selectList(query);
    }
}
