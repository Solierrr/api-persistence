package com.solaria.persistence.util;

public final class CacheKeyGenerator {
    private static final String PREFIX = "core";

    private CacheKeyGenerator() {}

    public static String supplierSearch(String filters) {
        return PREFIX + ":search:supplier:" + filters;
    }

    public static String searchCounter(String filters) {
        return PREFIX + ":counter:search:" + filters;
    }
}