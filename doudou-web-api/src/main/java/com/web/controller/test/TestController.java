package com.web.controller.test;

import com.common.jsonResult.JsonResult;
import com.service.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Api(value="TestController",tags={"测试类"})
public class TestController {

    @Autowired
    public RedisService redisService;

    @RequestMapping(value ="/redis", method= RequestMethod.GET)
    @ApiOperation(value="set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "值", required = true, dataType = "String"),
    })
    public JsonResult<Boolean> redis(String key,String value){
        boolean set = redisService.set(key, value);
        return null;
    }
    @RequestMapping(value ="/getRedis", method= RequestMethod.GET)
    @ApiOperation(value="get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "键", required = true, dataType = "String")
    })
    public JsonResult<String> redis(String key){
        String value = redisService.get(key);
        JsonResult<String> result = new JsonResult<>();
        result.setData(value);
        return result;
    }
}
