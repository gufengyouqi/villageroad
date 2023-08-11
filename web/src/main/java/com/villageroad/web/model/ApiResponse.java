package com.villageroad.web.model;

import lombok.Data;

import java.util.List;

/**
 * ApiResponse 使用泛型，方便反序列化
 * @param <T>
 */
@Data
public class ApiResponse<T> {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAIL = "FAIL";
    /**
     * 标明请求是否成功
     */
    private String status;
    /**
     * 错误编码
     */
    private String errorCode;
    /**
     * 错误信息,用作前端提示
     */
    private String errorDescription;
    /**
     * 存放多个返回结果,比如查询坐席列表的结果
     */
    private List<T> entities;
    /**
     * 存放单个返回结果,比如查询某个坐席的结果
     */
    private T entity;

    /**
     * 分页查询时使用,是否第一页
     */
    private Boolean first;
    /**
     * 分页查询时使用,是否最后一页
     */
    private Boolean last;
    /**
     * 一页的记录个数
     */
    private Integer size;
    /**
     * 当前页,从0开始
     */
    private Integer number;
    /**
     * 当前页的记录个数
     */
    private Integer numberOfElements;
    /**
     * 总共多少页
     */
    private Integer totalPages;
    /**
     * 总共多少记录
     */
    private Long totalElements;
    /**
     * 请求id
     */
    private String requestId;

}
