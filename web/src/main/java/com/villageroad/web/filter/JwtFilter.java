package com.villageroad.web.filter;

import com.villageroad.web.utils.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 每次请求的 Security 过滤类。执行jwt有效性检查，如果失败，不会设置 SecurityContextHolder 信息，会进入 AuthenticationEntryPoint
 * @author houshengbin
 */

@Component
public class JwtFilter extends OncePerRequestFilter {
 
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = jwtTokenProvider.resolveToken(request);
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                
                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication!", e);
        }
 
        filterChain.doFilter(request, response);
    }
 
}