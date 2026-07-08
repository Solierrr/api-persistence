package com.solaria.persistence.Exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String message) {
        super(message, HttpStatus.CONFLICT, "DUPLICATE_RESOURCE");
    }

    public DuplicateResourceException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.CONFLICT, "DUPLICATE_RESOURCE", properties);
    }
}
