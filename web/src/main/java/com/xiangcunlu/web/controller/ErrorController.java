package com.xiangcunlu.web.controller;

import freemarker.template.utility.NullArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义错误页面覆盖spring boot 中的错误页面
 */
@Slf4j
@Controller
public class ErrorController {
    @GetMapping("/400")
    public String badRequest(){
        return "error/400";
    }

    @GetMapping("/404")
    public String notFound(){
        return "error/404";
    }

    @GetMapping("/500")
    public String serverError(){
        return "error/500";
    }
}
