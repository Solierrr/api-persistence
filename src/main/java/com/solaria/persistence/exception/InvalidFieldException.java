package com.solaria.persistence.exception3;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class InvalidFieldException extends BusinessException {

    public InvalidFieldException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "INVALID_FIELD");
    }

    public InvalidFieldException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.BAD_REQUEST, "INVALID_FIELD", properties);
    }
}
