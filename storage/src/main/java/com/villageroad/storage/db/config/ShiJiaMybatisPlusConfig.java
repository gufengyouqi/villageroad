package com.villageroad.storage.db.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author houshengbin
 */
@Configuration
@MapperScan(basePackages = "com.villageroad.storage.db.shijia.mapper", sqlSessionFactoryRef = "shiJiaSqlSessionFactory")
public class ShiJiaMybatisPlusConfig {
    @Bean
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return properties -> {
            GlobalConfig globalConfig = properties.getGlobalConfig();
            globalConfig.setBanner(false);
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setDefaultEnumTypeHandler(MybatisEnumTypeHandler.class);
            properties.setConfiguration(configuration);
        };
    }
}
