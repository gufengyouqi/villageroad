package com.villageroad.exception;

import com.villageroad.model.BizError;
import lombok.Getter;

/**
 *
 * @author flybird
 * @date 2018/3/30
 */
public class BizException extends RuntimeException {

    public BizException(final BizError error) {
        super(error.getExplanation());
        this.errorCode = error.getCode();
    }

    public BizException(final BizError error, String message) {
        super(message);
        this.errorCode = error.getCode();
    }

    public BizException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(final BizError error, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = error.getCode();
    }

    public BizException(final BizError error, Throwable cause) {
        super(cause);
        this.errorCode = error.getCode();
    }

    @Getter
    private int errorCode;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()).append(", errorCode(").append(errorCode).append(")");
        Throwable cause = this.getCause();
        while (cause != null) {
            stringBuilder.append(" Caused by: ").append(cause);
            cause = cause.getCause();
        }
        return stringBuilder.toString();
    }
}
