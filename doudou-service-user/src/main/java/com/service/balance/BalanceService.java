package com.service.balance;

import com.common.jsonResult.JsonResult;
import com.service.model.Balance;

import java.math.BigDecimal;

public interface BalanceService {
    /**
     * 创建资金账户
     * @param userId    用户id
     * @return
     */
    JsonResult<Integer> saveBalance(long userId);

    /**
     * 获取资金信息
     * @param userId    用户id
     * @return
     */
    JsonResult<Balance> getBalance(long userId);

    /**
     * 修改状态
     * @param id        账户id
     * @param state     更新后的状态
     * @return
     */
    JsonResult<Integer> updateStateById(int id,int state);

    /**
     * 重置操作
     * @param userId    用户id
     * @param balance   可用资金
     * @param freeze    冻结资金
     * @return
     */
    JsonResult<Integer> updateBalance(long userId, BigDecimal balance,BigDecimal freeze);

    /**
     * 充值操作
     * @param userId    用户id
     * @param rechargeBalance   充值金额
     * @return
     */
    JsonResult<Integer> recharge(long userId, BigDecimal rechargeBalance);
}
