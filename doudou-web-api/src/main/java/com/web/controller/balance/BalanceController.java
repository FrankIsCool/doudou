package com.web.controller.balance;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.balance.BalanceService;
import com.service.model.Balance;
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

@RestController
@RequestMapping(value = "v1/user/balance")
@Api(value="BalanceController",tags={"账户信息"})
public class BalanceController {

    @Autowired
    public BalanceService balanceService;

    @RequestMapping(value ="/info", method= RequestMethod.GET)
    @ApiOperation(value="获取账户详细信息")
    public JsonResult<Balance> getBalanceInfo(HttpServletRequest request){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.getBalance(Long.valueOf(userId));
    }
    @RequestMapping(value ="/reset", method= RequestMethod.GET)
    @ApiOperation(value="重置账户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "balance", value = "可用资金", required = false, dataType = "BigDecimal"),
            @ApiImplicitParam(paramType="query", name = "freeze", value = "冻结资金", required = false, dataType = "BigDecimal")
    })
    public JsonResult<Integer> resetBalance(HttpServletRequest request, BigDecimal balance, BigDecimal freeze){
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.updateBalance(Long.valueOf(userId),balance,freeze);
    }
    @RequestMapping(value ="/recharge", method= RequestMethod.GET)
    @ApiOperation(value="重置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "balance", value = "充值资金", required = true, dataType = "BigDecimal"),
    })
    public JsonResult<Integer> rechargeBalance(HttpServletRequest request, BigDecimal balance){
        if(EmptyUtil.isEmpty(balance)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(request);
        String userId = tokenVo.getUserId();
        return balanceService.recharge(Long.valueOf(userId),balance);
    }
}
