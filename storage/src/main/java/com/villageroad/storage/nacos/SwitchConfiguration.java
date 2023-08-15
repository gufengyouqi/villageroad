package com.villageroad.storage.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;


/**
 * @author houshengbin
 */
@ConfigurationProperties("switcher")
@Data
@RefreshScope
public class SwitchConfiguration {
    private String test;
    private String test1;
    private String test2;
}
