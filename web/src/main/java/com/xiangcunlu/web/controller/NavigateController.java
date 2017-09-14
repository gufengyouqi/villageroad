package com.xiangcunlu.web.controller;

import freemarker.template.utility.NullArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class NavigateController {
    @RequestMapping("/")
    public String home(){

        System.out.println("test_home");
        throw new NullArgumentException();
//        return "home";
    }
}
