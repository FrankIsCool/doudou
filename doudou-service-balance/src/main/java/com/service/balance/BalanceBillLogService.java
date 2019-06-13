package com.service.balance;

import com.common.jsonResult.JsonResult;
import com.service.model.Balance;
import com.service.model.BalanceBillLog;

import java.math.BigDecimal;
import java.util.List;

public interface BalanceBillLogService {

    /**
     * 添加账户操作记录
     * @param balanceLog    操作记录实体类
     * @return
     */
    JsonResult<Integer> saveBalanceBillLog(BalanceBillLog balanceLog);

    /**
     * 获取资金操作记录
     * @param userId    用户id
     * @param pageNum   当前页数
     * @param pageSize  每页大小
     * @return
     */
    JsonResult<List<BalanceBillLog>> getBalanceBillLogs(long userId,Integer pageNum,Integer pageSize);

    /**
     * 获取资金操作记录
     * @param userId    用户id
     * @param startTime 开始统计时间
     * @param endTime   结束统计时间
     * @param operating 资金操作类型
     * @return
     */
    JsonResult<BigDecimal> getBalanceBillLogs(long userId, String startTime, String endTime, int operating);
    /**
     * 根据资金操作记录，计算出当前用户资金详情
     * @param userId        用户id
     * @param balanceId     资金账户id
     * @return
     */
    JsonResult<Balance> getBalances(long userId, long balanceId);

}
