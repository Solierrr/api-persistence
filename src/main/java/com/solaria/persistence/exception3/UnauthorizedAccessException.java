package com.solaria.persistence.exception3;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends BusinessException {

    public UnauthorizedAccessException(String message) {
        super(message, HttpStatus.FORBIDDEN, "UNAUTHORIZED_ACCESS");
    }

    public UnauthorizedAccessException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.FORBIDDEN, "UNAUTHORIZED_ACCESS", properties);
    }
}
