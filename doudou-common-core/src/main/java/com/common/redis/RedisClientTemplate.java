package com.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: nanjunyu
 * @Description:
 * @Date: Create in  2018/6/14 16:34
 */
@Service
public class RedisClientTemplate {
    private static final Logger log=LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private JedisClusterConfig jedisClusterConfig;

    public boolean set(String key,Object value){
        try {
            String str=jedisClusterConfig.getJedisCluster().set(key, String.valueOf(value));
            if("OK".equals(str))
                return true;
        }catch (Exception ex){
            log.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
        }
        return false;
    }

    public Object get(String key){
        String str=null;
        try {
            str=jedisClusterConfig.getJedisCluster().get(key);
        }catch (Exception ex){
            log.error("getRedis:{Key:"+key+"}",ex);
        }
        return str;
    }

}