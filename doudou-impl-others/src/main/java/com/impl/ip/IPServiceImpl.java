package com.impl.ip;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.ip.IPAddressVo;
import com.common.jsonResult.JsonResult;
import com.service.ip.IPService;

@Service()
public class IPServiceImpl implements IPService {
    public JsonResult<IPAddressVo> getAddress(String ip) {
        JsonResult<IPAddressVo> result = new JsonResult<IPAddressVo>();
        result.setData(null);
        return result;
    }
}
