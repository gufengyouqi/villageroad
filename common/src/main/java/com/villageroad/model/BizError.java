package com.villageroad.model;

/**
 * Created by wuhui on 2017/6/29.
 */
public enum BizError {

    /**
     * 内部错误
     */
    ERROR_INTERNAL(4000, "internal server error"),

    /**
     * 数据格式错误
     */
    ERROR_DATA_FORMAT(4001, "error format"),

    /**
     * user不存在
     */
    ERROR_USER_NOT_EXIST(4002, "user not exists"),

    /**
     * user已经存在
     */
    ERROR_ALREADY_EXIST(4003, "user already exists"),

    /**
     * 配置不存在
     */
    ERROR_VIDEO_CONFIG_NOT_EXIST(4004, "video config not exists"),

    /**
     * 不支持的操作
     */
    ERROR_VIDEO_CONFIG_NOT_SUPPORTED(4005, "not supported"),

    /**
     * 数据不合法
     */
    ERROR_DATA_RESTRICTION(4006, "restriction violation");

    private static final BizError[] errors = BizError.values();

    private final int code;
    private final String explanation;

    BizError(final int code, final String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public int getCode() {
        return code;
    }

    public static BizError valueOf(int code) {
        for (BizError error : errors) {
            if (error.code == code) {
                return error;
            }
        }
        return BizError.ERROR_INTERNAL;
    }

    public String getExplanation() {
        return explanation;
    }
}
