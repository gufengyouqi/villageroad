package com.xiangcunlu.web.controller;

import com.xiangcunlu.web.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author flybird
 * @date 2018/4/25
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    protected ResponseEntity<ApiResponse> createResponseEntity(Object entity) {
        ApiResponse response = new ApiResponse();
        response.setStatus("OK");
        response.setEntity(entity);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    protected ResponseEntity<ApiResponse> createResponseEntity(List<?> entities) {
        ApiResponse response = new ApiResponse();
        response.setStatus("OK");
        response.setEntities(entities);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
