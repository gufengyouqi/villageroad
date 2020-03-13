package com.villageroad.service;

import com.villageroad.storage.db.shijia.dao.BuyerDao;
import com.villageroad.storage.db.shijia.entity.Buyer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerService {
    @Resource
    private BuyerDao br;
// todo 客户信息收集

    public Buyer save(Buyer buyer){
         br.save(buyer);
         return buyer;
    }

}
