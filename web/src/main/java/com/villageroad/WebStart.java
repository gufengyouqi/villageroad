package com.villageroad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author houshengbin
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebMvc
@EnableRedisHttpSession
//@EnableTransactionManagement
//@EnableFeignClients(basePackages = {"com.villageroad.storage.feign"}, defaultConfiguration = {FeignClientConfig.class})
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class, args);
        int coreSize = Runtime.getRuntime().availableProcessors();
        // 启用并行GC
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(coreSize));
    }
}
