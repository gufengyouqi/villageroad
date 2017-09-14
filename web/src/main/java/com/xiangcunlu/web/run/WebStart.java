package com.xiangcunlu.web.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.xiangcunlu")
@SpringBootApplication
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);
    }
}
