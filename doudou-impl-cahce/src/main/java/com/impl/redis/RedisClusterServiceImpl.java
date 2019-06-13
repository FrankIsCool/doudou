package com.impl.redis;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.common.serialize.SerializeUtil;
import com.impl.config.JedisClusterConfig;
import com.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class RedisClusterServiceImpl implements RedisService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JedisClusterConfig jedisClusterConfig;


    @Override
    public boolean set(String key, String value) {
        try {
            String str=jedisClusterConfig.getJedisCluster().set(key, String.valueOf(value));
            if("OK".equals(str))
                return true;
        }catch (Exception ex){
            logger.error("redis save value is error：key="+key
                    +"，value"+ JSON.toJSONString(value),ex);
        }
        return false;
    }

    @Override
    public boolean set(String key, Object value) {
        try {
            String str=jedisClusterConfig.getJedisCluster().set(SerializeUtil.toBytes(key), SerializeUtil.toBytes(value));
            if("OK".equals(str))
                return true;
        }catch (Exception ex){
            logger.error("redis save value is error：key="+key
                    +"，value"+ JSON.toJSONString(value),ex);
        }
        return false;
    }

    @Override
    public Object getObject(String key){
//        String s = jedisClusterConfig.getJedisCluster().get(key);
        return null;
    }

    @Override
    public String get(String key) {
        String str=null;
        try {
            str=jedisClusterConfig.getJedisCluster().get(key);
        }catch (Exception ex){
            logger.error("redis get value is error：key="+key,ex);
        }
        return str;
    }
}
