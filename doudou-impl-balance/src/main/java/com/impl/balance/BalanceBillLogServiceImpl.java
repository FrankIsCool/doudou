package com.impl.balance;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.slaver.balance.BalanceBillLogMapper;
import com.service.balance.BalanceBillLogService;
import com.service.model.Balance;
import com.service.model.BalanceBillLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class BalanceBillLogServiceImpl implements BalanceBillLogService {

    @Autowired
    private BalanceBillLogMapper balanceBillLogMapper;

    @Override
    public JsonResult<Integer> saveBalanceBillLog(BalanceBillLog balanceLog) {
        if(EmptyUtil.isEmpty(balanceLog)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(balanceLog.getUserId())){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(balanceLog.getBalanceId())){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(balanceLog.getOperating())){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(balanceLog.getRemark())){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(balanceLog.getBalance())){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(balanceBillLogMapper.save(balanceLog));
        return result;
    }

    @Override
    public JsonResult<List<BalanceBillLog>> getBalanceBillLogs(long userId,Integer pageNum,Integer pageSize) {
        if(EmptyUtil.isEmpty(userId)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        if(EmptyUtil.isEmpty(pageNum)||pageNum<1){
            pageNum = 1;
        }
        if(EmptyUtil.isEmpty(pageSize)||pageSize<1){
            pageSize = 20;
        }
        pageNum=(pageNum-1)*pageSize;
        JsonResult<List<BalanceBillLog>> result = new JsonResult<>();
        result.setData(balanceBillLogMapper.getBalanceBillLogs(userId,pageNum,pageSize));
        return result;
    }

    @Override
    public JsonResult<BigDecimal> getBalanceBillLogs(long userId, String startTime, String endTime, int operating) {
        if(EmptyUtil.isEmpty(userId)||EmptyUtil.isEmpty(startTime)||
                EmptyUtil.isEmpty(endTime)||EmptyUtil.isEmpty(operating)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<BigDecimal> result = new JsonResult<>();
        result.setData(balanceBillLogMapper.getBalance(userId,startTime,endTime,operating));
        return result;
    }

    @Override
    public JsonResult<Balance> getBalances(long userId, long balanceId) {
        if(EmptyUtil.isEmpty(userId)||EmptyUtil.isEmpty(balanceId)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        BigDecimal rechargeBalances = balanceBillLogMapper.getBalances(userId, balanceId, BalanceBillLog.OPERATING_RECHARGE);
        BigDecimal consumptionBalances = balanceBillLogMapper.getBalances(userId, balanceId, BalanceBillLog.OPERATING_CONSUMPTION);
        BigDecimal freezeBalances = balanceBillLogMapper.getBalances(userId, balanceId, BalanceBillLog.OPERATING_FREEZE);
        BigDecimal unfreezeBalances = balanceBillLogMapper.getBalances(userId, balanceId, BalanceBillLog.OPERATING_UNFREEZE);
        if(EmptyUtil.isEmpty(rechargeBalances)){
            rechargeBalances = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
        }
        if(EmptyUtil.isEmpty(consumptionBalances)){
            consumptionBalances = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
        }
        if(EmptyUtil.isEmpty(freezeBalances)){
            freezeBalances = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
        }
        if(EmptyUtil.isEmpty(unfreezeBalances)){
            unfreezeBalances = new BigDecimal("0.00").setScale(2, BigDecimal.ROUND_DOWN);
        }
        Balance balance = new Balance();
        balance.setBalance(rechargeBalances.subtract(consumptionBalances));
        balance.setFreeze(freezeBalances.subtract(unfreezeBalances));
        balance.setTotalBalance(balance.getBalance().add(balance.getBalance()));
        balance.setUserId(userId);
        balance.setId(balanceId);
        JsonResult<Balance> result = new JsonResult<>();
        result.setData(balance);
        return result;
    }
}
