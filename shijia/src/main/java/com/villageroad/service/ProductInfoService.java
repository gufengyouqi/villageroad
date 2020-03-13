package com.villageroad.service;

import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductInfoService {
    @Resource
    private ProductInfoDao pir;

   public List<ProductInfo> findAll(){
       return pir.list();
   }
    public ProductInfo save(ProductInfo pi){
         pir.save(pi);
         return pi;
    }
    public void delete(Long id){
        pir.removeById(id);
    }



}
