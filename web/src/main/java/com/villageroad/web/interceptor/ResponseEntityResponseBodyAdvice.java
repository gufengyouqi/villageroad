package com.villageroad.web.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.villageroad.utils.JsonUtil;
import com.villageroad.web.constant.Constants;
import com.villageroad.web.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author houshengbin
 */
@Slf4j
@Order(3)
@ControllerAdvice(basePackages = "com.villageroad")
public class ResponseEntityResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@Nullable MethodParameter methodParameter, @Nullable Class converterType) {
        Class<?> returnType = methodParameter.getMethod().getReturnType();
        return returnType.isAssignableFrom(ResponseEntity.class) || returnType.isAssignableFrom(ResponseEntity.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, @Nullable MethodParameter returnType, @Nullable MediaType selectedContentType, @Nullable Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String requestId = MDC.get(Constants.REQUEST_ID_KEY);
        if (body instanceof ApiResponse) {
            ((ApiResponse) body).setRequestId(requestId);
            return body;
        }
        try {
            log.warn("to json fail  method:{}, uri:{}, requestId:{}, body:{}", request.getMethod(), request.getURI(), requestId, body);
            JSONObject bodyJson = JSONObject.parseObject(JsonUtil.toJson(body));
            bodyJson.put(Constants.REQUEST_ID_KEY, MDC.get(Constants.REQUEST_ID_KEY));
            return bodyJson;
        } catch (Throwable e) {
            log.error("to json fail  requestId:{}", requestId, e);
            return body;
        }
    }
}
