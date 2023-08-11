package com.villageroad.exception;

/**
 *
 * @author houshengbin
 * @date 2022/9/14
 */
public class FeignException extends RuntimeException {

    public FeignException(String message) {
        super(message);
    }
}
