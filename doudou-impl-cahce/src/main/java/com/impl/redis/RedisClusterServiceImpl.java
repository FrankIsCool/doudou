package com.impl.redis;

import com.alibaba.dubbo.config.annotation.Service;
import com.impl.config.JedisClusterConfig;
import com.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class RedisClusterServiceImpl implements RedisService {

    @Autowired
    private JedisClusterConfig jedisClusterConfig;


    @Override
    public boolean set(String key, String value) {
        try {
            String str=jedisClusterConfig.getJedisCluster().set(key, String.valueOf(value));
            if("OK".equals(str))
                return true;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public String get(String key){
        String str=null;
        try {
            str=jedisClusterConfig.getJedisCluster().get(key);
        }catch (Exception ex){
        }
        return str;
    }
}
