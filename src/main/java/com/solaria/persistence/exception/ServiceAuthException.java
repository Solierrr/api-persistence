package com.solaria.persistence.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class ServiceAuthException extends BusinessException {

    public ServiceAuthException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "SERVICE_AUTH_FAILED");
    }

    public ServiceAuthException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.UNAUTHORIZED, "SERVICE_AUTH_FAILED", properties);
    }
}
