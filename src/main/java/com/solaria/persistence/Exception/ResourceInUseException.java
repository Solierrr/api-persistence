package com.solaria.persistence.Exception;

import java.util.Map;

import org.springframework.http.HttpStatus;


public class ResourceInUseException extends BusinessException {

    public ResourceInUseException(String message) {
        super(message, HttpStatus.CONFLICT, "RESOURCE_IN_USE");
    }

    public ResourceInUseException(String message, Map<String, Object> properties) {
        super(message, HttpStatus.CONFLICT, "RESOURCE_IN_USE", properties);
    }
}
