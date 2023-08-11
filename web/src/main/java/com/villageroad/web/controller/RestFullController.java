package com.villageroad.web.controller;

import com.villageroad.storage.db.shijia.dao.ProductInfoDao;
import com.villageroad.storage.db.shijia.entity.ProductInfo;
import com.villageroad.storage.redis.RedisRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
    private RedisRepository redisRepository;

    @RequestMapping(value = "/test")
    public ResponseEntity test(HttpSession session) {
        List<ProductInfo> list = productInfoDao.list();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(123L);
        productInfo.setName("123");
        productInfo.setCreateTime(new Date());
        list.add(productInfo);
        System.out.println(list.size());
        Cookie[] cookies = this.request.getCookies();
        redisRepository.set("test", "test");
        redisRepository.set("test", productInfo);
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getValue());
        }
        System.out.println(redisRepository.get("test"));
        redisRepository.delete("test");
        session.setAttribute("test", "test");
        System.out.println(session.getId());
        return createResponseEntity(list);
    }

}

