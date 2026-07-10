package com.solaria.persistence.Exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND");
    }

    public ResourceNotFoundException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", properties);
    }
}
