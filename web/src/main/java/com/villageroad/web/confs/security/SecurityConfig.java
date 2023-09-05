package com.villageroad.web.confs.security;

import com.villageroad.web.filter.JwtFilter;
import com.villageroad.web.handler.AccessDeniedHandlerImpl;
import com.villageroad.web.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity 核心配置类
 * prePostEnabled = true 开启注解权限认证功能
 *
 * @author houshengbin
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    public static final String[] ENDPOINTS_WHITELIST = {
            "/favicon.ico",
            "/img/**",
            "/js/**",
            "/css/**",
            "/404",
            "/",
            "/login",
            "/auth/**",
            "/home"
    };
    public static final String LOGIN_URL = "/login.html";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/home";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    // 认证失败处理器
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    // 授权失败处理器
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(ENDPOINTS_WHITELIST)
                .requestMatchers(HttpMethod.OPTIONS, "/**");
    }

    /**
     * 认证配置
     * anonymous()：匿名访问：未登录可以访问，已登录不能访问
     * permitAll()：有没有认证都能访问：登录或未登录都能访问
     * denyAll(): 拒绝
     * authenticated()：认证之后才能访问
     * hasAuthority（）：包含权限
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用basic明文验证
                .httpBasic(AbstractHttpConfigurer::disable)
                // 前后端分离架构不需要csrf保护
                .csrf(AbstractHttpConfigurer::disable)
                // 禁用默认登录页
                .formLogin(formLogin->formLogin.loginPage(LOGIN_URL).permitAll().failureUrl(LOGIN_FAIL_URL).defaultSuccessUrl(DEFAULT_SUCCESS_URL))
                .rememberMe(rememberMe->rememberMe.rememberMeParameter("remember-me")
                        .userDetailsService(userDetailsService)
                        //设置过期时间
                        .tokenValiditySeconds(7*24*60*60)
                )
                // 禁用默认登出页
                .logout(formLogout->formLogout.logoutUrl(LOGOUT_URL).permitAll().logoutSuccessUrl(DEFAULT_SUCCESS_URL))
                // 前后端分离是无状态的，不需要session了，直接禁用。
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // 其他所有接口必须有Authority信息，Authority在登录成功后的UserDetailsImpl对象中默认设置“ROLE_USER”
                        //.requestMatchers("/**").hasAnyAuthority("ROLE_USER")
                        // 允许任意请求被已登录用户访问，不检查Authority
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                // 加我们自定义的过滤器，替代UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        // 配置异常处理器
        http.exceptionHandling(exceptions -> {
            // 认证失败
            exceptions.authenticationEntryPoint(authenticationEntryPoint);
            // 授权失败
            exceptions.accessDeniedHandler(accessDeniedHandler);
        });
        http.headers().frameOptions().disable();
        return http.build();
    }

    /**
     * 提供密码机密处理机制:
     * 将BCryptPasswordEncoder对象注入到spring容器中,更换掉原来的 PasswordEncoder加密方式
     * 原PasswordEncoder密码格式为：{id}password。它会根据id去判断密码的加密方式。
     * 如果没替换原来的加密方式，数据库中想用明文密码做测试，将密码字段改为{noop}123456这样的格式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 调用loadUserByUsername获得UserDetail信息，在AbstractUserDetailsAuthenticationProvider里执行用户状态检查
     *
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername 方法获取UserDetails
        authProvider.setUserDetailsService(userDetailsService);
        // 设置密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * 登录时需要调用AuthenticationManager.authenticate执行一次校验
     *
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    public static void main(String[] args) {
        // 创建BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin = passwordEncoder.encode("admin");
        System.out.println(admin);
        // 校验
        boolean isMatch = passwordEncoder.matches("ay123456",
                "$2a$10$8H0a2J2sH0pF9RODN.u/k.YSedkvo5QT57mqtqxQIjCZtpRbBhknK");
        boolean isMatch2 = passwordEncoder.matches("ay123456",
                "$2a$10$oD73ALotomxrEFuZFzRruOs17f8QNMnInl4d3CRd72dv3aw2LRd.S");
        System.out.println(isMatch);
        System.out.println(isMatch2);
    }

}