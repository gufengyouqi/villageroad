package com.villageroad.config;

import com.villageroad.interceptors.OkHttpInterceptor;
import feign.Feign;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@ConditionalOnProperty(value = "feign.okhttp.enabled")
@Slf4j
public class FeignClientConfig {
    @Autowired
    private OkHttpInterceptor okHttpInterceptor;

    private OkHttpClient okHttpClient;

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC;
    }

//    @Bean
//    @ConditionalOnMissingBean(ConnectionPool.class)
//    public ConnectionPool httpClientConnectionPool(
//            FeignHttpClientProperties httpClientProperties,
//            OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        int maxTotalConnections = httpClientProperties.getMaxConnections();
//        long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
//    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory httpClientFactory,
//                                             ConnectionPool connectionPool,
//                                             FeignClientProperties feignClientProperties,
//                                             FeignHttpClientProperties feignHttpClientProperties) {
//        FeignClientProperties.FeignClientConfiguration defaultConfig = feignClientProperties.getConfig().get("default");
//        int connectTimeout = feignHttpClientProperties.getConnectionTimeout();
//        int readTimeout = defaultConfig.getReadTimeout();
//        boolean disableSslValidation = feignHttpClientProperties.isDisableSslValidation();
//        boolean followRedirects = feignHttpClientProperties.isFollowRedirects();
//        this.okHttpClient = httpClientFactory.createBuilder(disableSslValidation)
//                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
//                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
//                .followRedirects(followRedirects)
//                .connectionPool(connectionPool)
//                .addInterceptor(okHttpInterceptor)
//                .build();
//        return this.okHttpClient;
//    }
//
//    @PreDestroy
//    public void destroy() {
//        if (okHttpClient != null) {
//            okHttpClient.dispatcher().executorService().shutdown();
//            okHttpClient.connectionPool().evictAll();
//        }
//    }
}