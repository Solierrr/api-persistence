package com.solaria.persistence.util;

import com.solaria.persistence.config.RedisProperties;
import org.springframework.stereotype.Component;

@Component
public final class CacheKeyGenerator {
    private final RedisProperties redisProperties;

    public CacheKeyGenerator(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    public String supplierSearch(String filters) {
        return redisProperties.getNamespace() + ":search:supplier:" + filters;
    }

    public String searchCounter(String filters) {
        return redisProperties.getNamespace() + ":counter:search:" + filters;
    }
}