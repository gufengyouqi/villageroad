package com.villageroad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author houshengbin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAsync
@EnableWebMvc
//@EnableTransactionManagement
//@EnableFeignClients(basePackages = {"com.villageroad.storage.feign"}, defaultConfiguration = {FeignClientConfig.class})
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);
    }
}
