package com.web.controller.others;

import com.common.ip.IPAddressVo;
import com.common.jsonResult.JsonResult;
import com.service.ip.IPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ip")
@Api(value="IPController",tags={"IP识别"})
public class IPController {

    @Autowired
    public IPService ipService;

    @RequestMapping(value ="/address", method= RequestMethod.GET)
    @ApiOperation(value="根据ip识别物理地址")
    @ApiImplicitParam(paramType="query", name = "ip", value = "IP地址", required = true, dataType = "String")
    public JsonResult<IPAddressVo> ipAddress(String ip){
        return ipService.getAddress(ip);
    }
    @RequestMapping(value ="/erro", method= RequestMethod.GET)
    @ApiOperation(value="异常检测")
    public JsonResult<IPAddressVo> ipAddress() throws NullPointerException {
        throw new NullPointerException("错误");
    }
}
