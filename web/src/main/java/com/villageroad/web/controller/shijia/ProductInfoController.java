package com.villageroad.web.controller.shijia;

import com.villageroad.config.Constant;
import com.villageroad.service.ProductInfoService;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import com.villageroad.web.controller.BaseController;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *
 * @author flybird
 * @date 2018/4/25
 */
@Controller
public class ProductInfoController extends BaseController {
    @Autowired
    private ProductInfoService pir;

    @PostMapping(value = "/productInfo")
    public ResponseEntity add(ProductInfo pi) {
        Object attribute = request.getSession().getAttribute(Constant.SELLER_SESSION_KEY);
        if (null == attribute) {
            return createResponseEntity("need to login", "fail");
        }
        pir.findAll();
        Cookie[] cookies = this.request.getCookies();
        for (Cookie cookie : cookies
        ) {
            System.out.println(cookie.getValue());
        }
        return createResponseEntity(true);
    }

    @DeleteMapping(value = "/productInfo")
    public ResponseEntity delete(Long id) {
        Object attribute = request.getSession().getAttribute(Constant.SELLER_SESSION_KEY);
        if (null == attribute) {
            return createResponseEntity("need to login", "fail");
        }
        pir.delete(id);

        return createResponseEntity(true);
    }

    @GetMapping(value = "/list")
    public ResponseEntity list() {
        List<ProductInfo> all = pir.findAll();
        return createResponseEntity(all);
    }
}

