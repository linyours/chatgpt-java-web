package com.blue.cat.utils;

import com.blue.cat.bean.constants.CommonConstant;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(CommonConstant.CACHE_TIME_OUT, TimeUnit.SECONDS).build();

    public static void put(String key, String value) {
        cache.put(key, value);
    }

    public static String getIfPresent(String key){
        return cache.getIfPresent(key);
    }


    /**
     * 获取所有的登录的用户信息
     * @return
     */
    public static ConcurrentMap<String, String>  getAllCacheUserInfo(){
        return cache.asMap();
    }

    /**
     * 获取所有的缓存的用户信息
     * @return
     */
    public static Collection<String> getUserInfo(){
        return cache.asMap().values();
    }


    public static void invalidate(String key){
        cache.invalidate(key);
    }

    /**
     * 删除登录缓存
     * @param key
     */
    public static void removeCache(String key){
        cache.asMap().remove(key);
    }

}
