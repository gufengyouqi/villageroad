package com.villageroad.storage.db.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
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
    // shiJiaDataSource
    @Bean("shiJiaDataSource")
    @ConfigurationProperties("spring.datasource.shijia")
    public HikariDataSource dataSource1() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "shiJiaJdbcTemplate")
    public JdbcTemplate jdbcTemplate1(@Qualifier("shiJiaDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "shiJiaSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("shiJiaDataSource") DataSource dataSource) throws Exception {
        return createSqlSessionFactory(dataSource);
    }

    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(new MybatisConfiguration());
        return factoryBean.getObject();
    }


//    @Bean
//    @ConfigurationProperties("spring.datasource.db2")
//    public HikariDataSource dataSource2() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
}
