package com.impl.ip;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.ip.IPAddressVo;
import com.common.ip.IPUtil;
import com.common.jsonResult.JsonResult;
import com.service.ip.IPService;
import com.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IPServiceImpl implements IPService {

    @Autowired
    private RedisService redisService;

    /**
     * 根据ip获取地址
     * @param ip
     * @return
     */
    @Override
    public JsonResult<IPAddressVo> getAddress(String ip) {
        JsonResult<IPAddressVo> result = new JsonResult<>();
        IPAddressVo address = (IPAddressVo) redisService.getObject(ip);
        if(EmptyUtil.isNotEmpty(address)){
            result.setData(address);
            return result;
        }
        address = IPUtil.getAddress(ip);
        if(EmptyUtil.isNotEmpty(address)){
            redisService.set(ip, address);
            result.setData(address);
        }
        return result;
    }
}
