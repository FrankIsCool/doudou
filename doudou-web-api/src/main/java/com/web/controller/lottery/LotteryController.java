package com.web.controller.lottery;

import com.common.jsonResult.JsonResult;
import com.common.lottery.LotteryHistoryData;
import com.common.lottery.LotteryResultData;
import com.common.lottery.LotteryTypeData;
import com.service.lottery.LotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/lottery")
@Api(value="LotteryController",tags={"彩票"})
public class LotteryController {

    @Autowired
    public LotteryService lotteryService;

    @RequestMapping(value ="/type", method= RequestMethod.GET)
    @ApiOperation(value="彩票类型")
    public JsonResult<LotteryTypeData> getLotteryType(){
        return lotteryService.getLotteryType();
    }
    @RequestMapping(value ="/result", method= RequestMethod.GET)
    @ApiOperation(value="彩票结果")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "lotteryId", value = "彩票id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "lotteryNo", value = "彩票期号,默认最近一期", required = false, dataType = "String")
    })
    public JsonResult<LotteryResultData> getLotteryResult(String lotteryId, String lotteryNo){
        return lotteryService.getLotteryResult(lotteryId,lotteryNo);
    }
    @RequestMapping(value ="/history", method= RequestMethod.GET)
    @ApiOperation(value="彩票结果")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "lotteryId", value = "彩票id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页大小，默认10", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页数，默认1", required = false, dataType = "String")
    })
    public JsonResult<LotteryHistoryData> getLotteryHistory(String lotteryId, int pageSize, int page){
        if(pageSize<10){
            pageSize = 10;
        }
        if(page<1){
            pageSize = 1;
        }
        return lotteryService.getLotteryHistory(lotteryId,pageSize,page);
    }
}
