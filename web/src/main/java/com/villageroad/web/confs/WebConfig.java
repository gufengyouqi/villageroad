package com.villageroad.web.confs;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.villageroad.web.interceptor.RequestIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * spring mvc配置类
 *
 * @author houshengbin
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * 资源跨域设置Cors
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**设置允许跨域的路径**/
        registry.addMapping("/**")
                /** 允许跨域访问的域名 **/
                .allowedOriginPatterns("*")
                /** 请求方法 HttpMethod.DELETE/POST/GET/PUT/DELETE/OPTIONS **/
                .allowedMethods("*")
                .allowedHeaders("*")
                /** 是否允许证书 不再默认开启 **/
                .allowCredentials(true)
                /** 预检请求的有效期，单位为秒。**/
                .maxAge(3600);
    }

    /**
     * 解决  No mapping for GET /favicon.ico 访问静态资源图标
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 请求拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new RequestIdInterceptor());
//        loggerInterceptor.addInterceptor(registry);
//        myWebInterceptor.addInterceptor(registry);
    }

    /**
     * 定义全局默认时间序列化
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .dateFormat(new SimpleDateFormat(DATE_FORMAT))
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .simpleDateFormat(DATE_TIME_FORMAT)
                .serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .modulesToInstall(new ParameterNamesModule());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
}

