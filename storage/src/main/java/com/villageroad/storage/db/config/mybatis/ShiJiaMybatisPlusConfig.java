package com.villageroad.storage.db.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author houshengbin
 */
@Configuration
@MapperScan(basePackages = "com.villageroad.storage.db.shijia.mapper", sqlSessionFactoryRef = "shiJiaSqlSessionFactory")
public class ShiJiaMybatisPlusConfig {

}
