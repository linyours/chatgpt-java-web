package com.blue.cat.utils;

import com.blue.cat.bean.constants.LimitEnum;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IpCacheUtil {

    private static Cache<String, AtomicInteger> cache = CacheBuilder.newBuilder().expireAfterWrite(LimitEnum.IP.getTime(), TimeUnit.SECONDS).build();

    public static void put(String key, AtomicInteger value) {
        cache.put(key, value);
    }

    public static AtomicInteger getIfPresent(String key){
        return cache.getIfPresent(key);
    }

    /**
     * 删除缓存
     * @param key
     */
    public static void removeCache(String key){
        cache.asMap().remove(key);
    }

}
