package com.xiangcunlu.web.confs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author flybird
 * 说明：跨域请求
 * js 测试脚本
 * <pre>$.ajax({
 * type: 'GET',
 * url: "http://localhost:8089/test",
 * xhrFields: {
 * withCredentials: true
 * },
 * success: function (re) {
 * console.log(re)
 * }
 * });</pre>
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                // 允许跨域访问的域名
                .allowedOrigins("*")
                // 请求方法 HttpMethod.DELETE/POST/GET/PUT/DELETE/OPTIONS
                .allowedMethods("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                // 预检请求的有效期，单位为秒。
                .allowCredentials(true).maxAge(3600);
    }

}