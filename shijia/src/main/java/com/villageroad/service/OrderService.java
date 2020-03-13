package com.villageroad.service;

import com.villageroad.storage.db.shijia.dao.OrderDetailDao;
import com.villageroad.storage.db.shijia.dao.OrderMasterDao;
import com.villageroad.storage.db.shijia.entity.OrderDetail;
import com.villageroad.storage.db.shijia.entity.OrderMaster;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Resource
    private OrderMasterDao omr;

    @Resource
    private OrderDetailDao odr;

    public OrderMaster save(OrderMaster om, List<OrderDetail> orderDetails){
        omr.save(om);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(om.getId());
        }
         odr.saveBatch(orderDetails);
        return om;
    }

}
