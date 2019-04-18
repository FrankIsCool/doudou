package com.service.ip;

import com.common.ip.IPAddressVo;
import com.common.jsonResult.JsonResult;

public interface IPService {

    JsonResult<IPAddressVo> getAddress(String ip);
}
