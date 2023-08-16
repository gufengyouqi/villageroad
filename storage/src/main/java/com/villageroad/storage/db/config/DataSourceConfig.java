package com.villageroad.storage.db.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.p6spy.engine.spy.P6SpyDriver;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author houshengbin
 */
@Configuration
@EnableConfigurationProperties
public class DataSourceConfig {
    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Autowired
    private GlobalConfig globalConfig;

    @Value("${spring.profiles.active}")
    private String active;

    private static final String ACTIVE_DEV = "dev";

    // shiJiaDataSource
    @Bean("shiJiaDataSource")
    @ConfigurationProperties("spring.datasource.shijia")
    public HikariDataSource dataSource1() {
         return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "shiJiaJdbcTemplate")
    public JdbcTemplate jdbcTemplate1(@Qualifier("shiJiaDataSource") HikariDataSource ds) {
        // dev环境下使用p6spy
        if (ACTIVE_DEV.equals(active)) {
            String jdbcUrl = "jdbc:p6spy:mysql://127.0.0.1:3307/villageroad?allowPublicKeyRetrieval=true&characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai";
            String driverClassName = P6SpyDriver.class.getName();
            ds.setDriverClassName(driverClassName);
            ds.setJdbcUrl(jdbcUrl);
        }
        return new JdbcTemplate(ds);
    }

    @Bean(name = "shiJiaSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("shiJiaDataSource") DataSource dataSource) throws Exception {
        return createSqlSessionFactory(dataSource);
    }

    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setGlobalConfig(globalConfig);
        factoryBean.setDataSource(dataSource);
        // 配置
        factoryBean.setConfiguration(mybatisConfiguration);
        factoryBean.setPlugins(mybatisPlusInterceptor);
        // 设置全局配置
        return factoryBean.getObject();
    }


//    @Bean
//    @ConfigurationProperties("spring.datasource.db2")
//    public HikariDataSource dataSource2() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
}
