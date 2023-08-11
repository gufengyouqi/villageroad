package com.villageroad.web.interceptor;

import com.villageroad.web.constant.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author houshengbin
 */
public class RequestIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getParameter(Constants.REQUEST_ID_KEY);
        if (StringUtils.isBlank(requestId)) {
            requestId = String.valueOf(UUID.randomUUID().toString());
        }
        MDC.put(Constants.REQUEST_ID_KEY, requestId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(Constants.REQUEST_ID_KEY);
    }


}
