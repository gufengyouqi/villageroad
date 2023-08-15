package com.villageroad.storage.db.config;

import com.villageroad.storage.nacos.SwitchConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author houshengbin
 */
@Configuration
@EnableDiscoveryClient
@EnableConfigurationProperties({SwitchConfiguration.class})
public class NacosConfig {

}
