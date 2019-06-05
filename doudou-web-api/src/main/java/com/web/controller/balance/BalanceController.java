package com.web.controller.balance;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.balance.BalanceBillLogService;
import com.service.balance.BalanceService;
import com.service.model.Balance;
import com.service.model.BalanceBillLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "v1/user/balance")
@Api(value="BalanceController",tags={"账户信息"})
public class BalanceController {

    @Autowired
    public BalanceService balanceService;
    @Autowired
    public BalanceBillLogService balanceBillLogService;
    @RequestMapping(value ="/info", method= RequestMethod.GET)
    @ApiOperation(value="获取账户详细信息")
    public JsonResult<Balance> getBalanceInfo(HttpServletRequest request){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.getBalance(Long.valueOf(userId));
    }
    @RequestMapping(value ="/reset", method= RequestMethod.GET)
    @ApiOperation(value="重置账户详细信息")
    public JsonResult<Integer> resetBalance(HttpServletRequest request){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.updateBalance(Long.valueOf(userId));
    }
    @RequestMapping(value ="/recharge", method= RequestMethod.GET)
    @ApiOperation(value="充值")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "balance", value = "充值金额", required = true, dataType = "BigDecimal"),
    })
    public JsonResult<Integer> rechargeBalance(HttpServletRequest request, BigDecimal balance){
        if(EmptyUtil.isEmpty(balance)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.recharge(Long.valueOf(userId),balance);
    }
    @RequestMapping(value ="/operating/info", method= RequestMethod.GET)
    @ApiOperation(value="获取账户操作详细")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "每页大小", required = false, dataType = "Integer"),
    })
    public JsonResult<List<BalanceBillLog>> getBalanceOperatingInfo(HttpServletRequest request,Integer pageNum,Integer pageSize){
        if(EmptyUtil.isEmpty(pageNum)||pageNum<1){
            pageNum = 1;
        }
        if(EmptyUtil.isEmpty(pageSize)||pageSize<1){
            pageSize = 20;
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceBillLogService.getBalanceBillLogs(Long.valueOf(userId),pageNum,pageSize);
    }
    @RequestMapping(value ="/consumption", method= RequestMethod.GET)
    @ApiOperation(value="消费")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "consumption", value = "消费金额", required = true, dataType = "BigDecimal"),
    })
    public JsonResult<Integer> consumptionBalance(HttpServletRequest request, BigDecimal consumption){
        if(EmptyUtil.isEmpty(consumption)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.consumption(Long.valueOf(userId),consumption);
    }
}
