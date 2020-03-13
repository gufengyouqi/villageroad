package com.villageroad.web.controller;

import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by flybird on 2018/4/25.
 */
@Slf4j
@RestController
public class RestFullController extends BaseController {
    private static final String MEDIA_DIR = "/home/user/media_upload";
    @Autowired
    private ProductInfoDao pir;

    @RequestMapping(value = "/test")
    public ResponseEntity test() {
        List<ProductInfo> list = pir.list();
        System.out.println(list.size());
        Cookie[] cookies = this.request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
        }
        return createResponseEntity(true);
    }

}

