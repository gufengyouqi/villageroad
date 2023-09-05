package com.villageroad.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author houshengbin
 */
public class InvalidJwtAuthenticationException extends AuthenticationException {

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}