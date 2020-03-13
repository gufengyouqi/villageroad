package com.villageroad.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class NavigateController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
