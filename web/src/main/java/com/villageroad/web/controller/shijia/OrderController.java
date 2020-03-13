package com.villageroad.web.controller.shijia;

import com.villageroad.service.OrderService;
import com.villageroad.storage.db.shijia.entity.OrderDetail;
import com.villageroad.storage.db.shijia.entity.OrderMaster;
import com.villageroad.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by flybird on 2018/4/25.
 */
@Controller(value = "/eatBest")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order")
    public ResponseEntity create(OrderMaster om, List<OrderDetail> orderDetails) {
        OrderMaster save = orderService.save(om, orderDetails);
        return createResponseEntity(save);
    }

}

