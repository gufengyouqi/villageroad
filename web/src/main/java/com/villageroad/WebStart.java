package com.villageroad;

import com.villageroad.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author houshengbin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
//@EnableFeignClients(basePackages = {"com.villageroad.storage.feign"}, defaultConfiguration = {FeignClientConfig.class})
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);
    }
}
