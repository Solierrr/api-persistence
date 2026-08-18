package com.solaria.persistence.service;

import com.solaria.persistence.dto.request.SupplierSearchFilterDTO;
import com.solaria.persistence.dto.response.SupplierSearchResponseDTO;
import com.solaria.persistence.util.CacheKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class SupplierCacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierCacheService.class);
    private static final Duration SEARCH_CACHE_TTL = Duration.ofMinutes(10);
    
}
