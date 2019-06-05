package com.service.lottery;

import com.common.jsonResult.JsonResult;
import com.common.lottery.LotteryHistoryData;
import com.common.lottery.LotteryResultData;
import com.common.lottery.LotteryTypeData;

import java.util.List;

public interface LotteryService {
    /**
     * 获取彩票类型
     * @return
     */
    JsonResult<List<LotteryTypeData>> getLotteryType();
    /**
     * 获取彩票结果
     * @param lotteryId 彩票id
     * @param lotteryNo 彩票编码（可为空）
     * @return
     */
    JsonResult<LotteryResultData> getLotteryResult(String lotteryId,String lotteryNo);
    /**
     * 获取彩票历史结果
     * @param lotteryId 彩票id
     * @param pageSize  每页大小
     * @param page      当前页数
     * @return
     */
    JsonResult<LotteryHistoryData> getLotteryHistory(String lotteryId,int pageSize,int page);
}
