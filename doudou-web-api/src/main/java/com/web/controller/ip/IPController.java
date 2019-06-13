package com.web.controller.ip;

import com.common.ip.IPAddressVo;
import com.common.ip.IPUtil;
import com.common.jsonResult.JsonResult;
import com.service.ip.IPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "v1/ip")
@Api(value="IPController",tags={"ip解析"})
public class IPController {

    @Autowired
    public IPService ipService;

    @RequestMapping(value ="/address", method= RequestMethod.GET)
    @ApiOperation(value="ip解析")
    public JsonResult<IPAddressVo> getAddressByIp(HttpServletRequest request){
        return ipService.getAddress(IPUtil.getIpAddr(request));
    }

}
