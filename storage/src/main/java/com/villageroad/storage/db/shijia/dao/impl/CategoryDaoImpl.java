package com.villageroad.storage.db.shijia.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.villageroad.storage.db.shijia.dao.BuyerDao;
import com.villageroad.storage.db.shijia.dao.CategoryDao;
import com.villageroad.storage.db.shijia.entity.Buyer;
import com.villageroad.storage.db.shijia.entity.Category;
import com.villageroad.storage.db.shijia.mapper.BuyerMapper;
import com.villageroad.storage.db.shijia.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * @author houshengbin
 */
@Service
public class CategoryDaoImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryDao {

}
