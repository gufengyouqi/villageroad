package com.villageroad.web.controller;

import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import com.villageroad.storage.nacos.SwitchConfiguration;
import com.villageroad.storage.redis.RedisCache;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *
 * @author flybird
 * @date 2018/4/25
 */
@Slf4j
@RestController
public class RestFullController extends BaseController {
    private static final String MEDIA_DIR = "/home/user/media_upload";
    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SwitchConfiguration switchConfiguration;

    @RequestMapping(value = "/test")
    public ResponseEntity test(HttpSession session) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setName("test");
        productInfo.setPrice(10F);
        productInfo.setStock(100);
        productInfo.setDescription("test");
        productInfoDao.save(productInfo);
        List<ProductInfo> list = productInfoDao.list();
        Cookie[] cookies = this.request.getCookies();
        redisCache.setCacheObject("test", "test");
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
        }
        System.out.println(switchConfiguration.getTest());
        System.out.println((String) redisCache.getCacheObject("test"));
        redisCache.deleteObject("test");
        session.setAttribute("test", "test");
        System.out.println(session.getId());
        return createResponseEntity(list);
    }

}

