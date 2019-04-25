package com.impl.ip;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.ip.IPAddressVo;
import com.common.ip.ali.AliIPUtil;
import com.common.ip.ali.FSIPResult;
import com.common.jsonResult.JsonResult;
import com.service.ip.IPService;

@Service
public class IPServiceImpl implements IPService {
    public JsonResult<IPAddressVo> getAddress(String ip) {
        JsonResult<IPAddressVo> result = new JsonResult<IPAddressVo>();
        FSIPResult addressByFS = AliIPUtil.getAddressByFS(ip);
        IPAddressVo ipAddressVo = new IPAddressVo();
        ipAddressVo.setCity(addressByFS.getData().getCity());
        ipAddressVo.setCountry(addressByFS.getData().getCountry());
        ipAddressVo.setIp(ip);
        ipAddressVo.setIsp(addressByFS.getData().getIsp());
        ipAddressVo.setProvince(addressByFS.getData().getProv());
        result.setData(ipAddressVo);
        return result;
    }
}
