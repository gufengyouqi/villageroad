package com.villageroad.storage.db.shijia.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.villageroad.storage.db.shijia.entity.ProductInfo;

import java.util.List;

/**
 * @author houshengbin
 */
public interface ProductInfoDao extends IService<ProductInfo> {
    /**
     * 根据商品状态查询商品
     *
     * @param status
     * @return
     */
    public List<ProductInfo> findByStatus(Integer status);
}
