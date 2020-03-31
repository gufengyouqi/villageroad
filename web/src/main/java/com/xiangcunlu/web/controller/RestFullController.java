package com.xiangcunlu.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

/**
 * Created by flybird on 2018/4/25.
 */
@RestController
public class RestFullController extends BaseController {

    @RequestMapping(value = "/test")
    public ResponseEntity test() {
        Cookie[] cookies = this.request.getCookies();
        for (Cookie cookie : cookies
                ) {
            System.out.println(cookie.getValue());
        }
        return createResponseEntity(true);
    }

    public static void main(String[] args) {
        Integer a=1;
        System.out.println((long)a);
    }
}

