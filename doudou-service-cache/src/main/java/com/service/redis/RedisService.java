package com.service.redis;

public interface RedisService {

    boolean set(String key,String value);

    boolean set(String key,Object value);

    String get(String key);

    Object getObject(String key);
}
