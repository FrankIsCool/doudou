package com.service.ip;


import com.common.ip.IPAddressVo;
import com.common.jsonResult.JsonResult;

public interface IPService {
    /**
     * 根据ip获取物理地址
     * @param ip    IP地址
     * @return
     */
    JsonResult<IPAddressVo> getAddress(String ip);
}
