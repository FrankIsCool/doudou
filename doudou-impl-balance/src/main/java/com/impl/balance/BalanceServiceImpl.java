package com.impl.balance;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.master.balance.BalanceMapper;
import com.service.balance.BalanceBillLogService;
import com.service.balance.BalanceService;
import com.service.model.Balance;
import com.service.model.BalanceBillLog;
import org.apache.log4j.Logger;
//import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
//    private static final Logger log = LoggerFactory.getLogger(BalanceService.class);
    private static final Logger log = Logger.getLogger(BalanceService.class);

    @Autowired
    private BalanceMapper balanceMapper;
    @Autowired
    private BalanceBillLogService balanceBillLogService;

    @Override
    public JsonResult<Integer> saveBalance(long userId) {
        JsonResult<Integer> result = new JsonResult<>();
        Balance balance = initSaveBalance(userId);
        result.setData(balanceMapper.save(balance));
        return result;
    }

    /**
     * 初始化账户信息
     * @param userId
     * @return
     */
    private Balance initSaveBalance(long userId){
        Balance balance = new Balance();
        balance.setUserId(userId);
        balance.setState(Balance.STATE_NORMAL);
        balance.setBalance(new BigDecimal("0.00"));
        balance.setFreeze(new BigDecimal("0.00"));
        balance.setTotalBalance(new BigDecimal("0.00"));
        balance.setMd5(getBalanceMd5(balance));
        return balance;
    }
    /**
     * 验证账户
     * @param balance
     * @return
     */
    private JsonResult<Integer> verifiBalance(Balance balance){
        if(balance.getState()==Balance.STATE_ABNORMAL){
            return JsonResult.error(ExceptionCode.ERRO_103000);
        }
        if(balance.getState()==Balance.STATE_FREEZE){
            return JsonResult.error(ExceptionCode.ERRO_103001);
        }
        String balanceMd5 = getBalanceMd5(balance);
        if(!balanceMd5.equals(balance.getMd5())){
            updateStateById(balance.getId(),Balance.STATE_ABNORMAL);
            return JsonResult.error(ExceptionCode.ERRO_103000);
        }
        if(balance.getTotalBalance().compareTo(balance.getBalance().add(balance.getFreeze()))!=0){
            updateStateById(balance.getId(),Balance.STATE_ABNORMAL);
            return JsonResult.error(ExceptionCode.ERRO_103000);
        }
        return new JsonResult<>();
    }
    /**
     * 获取加密生成md5
     * @param balance
     * @return
     */
    public String getBalanceMd5(Balance balance){
        StringBuilder str = new StringBuilder();
        str.append(balance.getBalance()).append("&&").append(balance.getFreeze()).append("&&").append(balance.getTotalBalance()).append("doudou#@!");
        return DigestUtils.md5DigestAsHex(str.toString().getBytes());
    }
    @Override
    public JsonResult<Balance> getBalance(long userId) {
        log.info(" i am info");
        log.error("i am error");
        log.warn(" i am warn");
        log.debug(" i am debug");
        Balance balance = balanceMapper.getBalanceByUserId(userId);
        if(EmptyUtil.isEmpty(balance)){
            JsonResult<Integer> result1 = saveBalance(userId);
            if(result1.getData()<1){
                return JsonResult.error(ExceptionCode.ERRO_500);
            }
            balance = balanceMapper.getBalanceByUserId(userId);
        }
        JsonResult jsonResult = verifiBalance(balance);
        if(!jsonResult.getCode().equals(JsonResult.SUCCESS)){
            return jsonResult;
        }
        JsonResult<Balance> result = new JsonResult<>();
        balance.setMd5(null);
        result.setData(balance);
        return result;
    }

    @Override
    public JsonResult<Integer> updateStateById(long id, int state) {
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(balanceMapper.updateState(id,state));
        return result;
    }

    @Override
    public JsonResult<Integer> updateBalance(long userId) {
        Balance balances = balanceMapper.getBalanceByUserId(userId);
        JsonResult<Integer> jsonResult = verifiBalance(balances);
        if(jsonResult.getCode()==JsonResult.SUCCESS){
            JsonResult<Integer> result = new JsonResult<>();
            result.setData(1);
            return result;
        }
        JsonResult<Balance> balancesResult = balanceBillLogService.getBalances(userId, balances.getId());
        if(balancesResult.getCode()!=JsonResult.SUCCESS){
            return JsonResult.error(balancesResult.getCode(),balancesResult.getMsg());
        }
        balances = balancesResult.getData();
        balances.setMd5(getBalanceMd5(balances));
        balances.setState(Balance.STATE_NORMAL);
        int update = balanceMapper.updateBalance(balances);
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(update);
        return result;
    }

    @Override
    public JsonResult<Integer> recharge(long userId, BigDecimal rechargeBalance) {
        Balance balance = balanceMapper.getBalanceByUserId(userId);
        JsonResult jsonResult = verifiBalance(balance);
        if(!jsonResult.getCode().equals(JsonResult.SUCCESS)){
            return jsonResult;
        }
        balance.setBalance(balance.getBalance().add(rechargeBalance));
        balance.setTotalBalance(balance.getTotalBalance().add(rechargeBalance));
        balance.setMd5(getBalanceMd5(balance));
        int update = balanceMapper.updateBalance(balance);
        if(update>0){
            BalanceBillLog balanceBillLog = new BalanceBillLog();
            balanceBillLog.setBalance(rechargeBalance);
            balanceBillLog.setUserId(userId);
            balanceBillLog.setBalanceId(balance.getId());
            balanceBillLog.setOperating(BalanceBillLog.OPERATING_RECHARGE);
            balanceBillLog.setRemark("充值金额："+rechargeBalance+"/元");
            JsonResult<Integer> result = balanceBillLogService.saveBalanceBillLog(balanceBillLog);
            if(result.getData()<1){
                return JsonResult.error(ExceptionCode.ERRO_500);
            }
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(update);
        return result;
    }

    @Override
    public JsonResult<Integer> consumption(long userId, BigDecimal consumptionBalance) {
        Balance balance = balanceMapper.getBalanceByUserId(userId);
        JsonResult jsonResult = verifiBalance(balance);
        if(!jsonResult.getCode().equals(JsonResult.SUCCESS)){
            return jsonResult;
        }
        balance.setBalance(balance.getBalance().subtract(consumptionBalance));
        balance.setTotalBalance(balance.getTotalBalance().subtract(consumptionBalance));
        balance.setMd5(getBalanceMd5(balance));
        int update = balanceMapper.updateBalance(balance);
        if(update>0){
            BalanceBillLog balanceBillLog = new BalanceBillLog();
            balanceBillLog.setBalance(consumptionBalance);
            balanceBillLog.setUserId(userId);
            balanceBillLog.setBalanceId(balance.getId());
            balanceBillLog.setOperating(BalanceBillLog.OPERATING_CONSUMPTION);
            balanceBillLog.setRemark("消费金额："+consumptionBalance+"/元");
            JsonResult<Integer> result = balanceBillLogService.saveBalanceBillLog(balanceBillLog);
            if(result.getData()<1){
                return JsonResult.error(ExceptionCode.ERRO_500);
            }
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(update);
        return result;
    }
}
