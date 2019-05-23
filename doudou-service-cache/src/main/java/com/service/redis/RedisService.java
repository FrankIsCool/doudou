package com.service.redis;

public interface RedisService {
    /**
     * 缓存数据
     * @param key   键
     * @param value 值
     * @return
     */
    boolean set(String key,String value);
    /**
     * 缓存数据
     * @param key   键
     * @param value 实体类
     * @return
     */
    boolean set(String key,Object value);
    /**
     * 获取缓存信息
     * @param key   键
     * @return
     */
    String get(String key);
    /**
     * 获取缓存信息
     * @param key   键
     * @return
     */
    Object getObject(String key);
}
