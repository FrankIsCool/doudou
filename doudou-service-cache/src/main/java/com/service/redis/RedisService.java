package com.service.redis;

public interface RedisService {

    boolean set(String key,String value);

    String get(String key);
}
