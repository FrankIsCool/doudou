package com.impl.lottery;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.jsonResult.JsonResult;
import com.common.lottery.JHLotteryUtil;
import com.common.lottery.LotteryHistoryData;
import com.common.lottery.LotteryResultData;
import com.common.lottery.LotteryTypeData;
import com.common.redis.RedisConfig;
import com.common.weather.juhe.JHResult;
import com.service.lottery.LotteryService;
import com.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    private RedisService redisService;

    @Override
    public JsonResult<List<LotteryTypeData>> getLotteryType() {
        List<LotteryTypeData> lotteryTypeData = (List<LotteryTypeData>) redisService.getObject(RedisConfig.LOTTERYTYPE);
        if(EmptyUtil.isNotEmpty(lotteryTypeData)){
            return JsonResult.success(lotteryTypeData);
        }
        JHResult<List<LotteryTypeData>> resultLotteryType = JHLotteryUtil.getLotteryType();
        if(EmptyUtil.isEmpty(resultLotteryType.getResult())){
            return JsonResult.success();
        }
        redisService.set(RedisConfig.LOTTERYTYPE, resultLotteryType.getResult());
        return JsonResult.success(resultLotteryType.getResult());
    }

    @Override
    public JsonResult<LotteryResultData> getLotteryResult(String lotteryId, String lotteryNo) {
        LotteryResultData lotteryResultData = (LotteryResultData) redisService.getObject(RedisConfig.LOTTERY+lotteryId+"&"+lotteryNo);
        if(EmptyUtil.isNotEmpty(lotteryResultData)){
            return JsonResult.success(lotteryResultData);
        }
        JHResult<LotteryResultData> resultLotteryType = JHLotteryUtil.getLotteryResult(lotteryId,lotteryNo);
        if(EmptyUtil.isEmpty(resultLotteryType.getResult())){
            return JsonResult.success();
        }
        redisService.set(RedisConfig.LOTTERY+lotteryId+"&"+lotteryNo, resultLotteryType.getResult());
        return JsonResult.success(resultLotteryType.getResult());
    }

    @Override
    public JsonResult<LotteryHistoryData> getLotteryHistory(String lotteryId, int pageSize, int page) {
        JHResult<LotteryHistoryData> resultLotteryType = JHLotteryUtil.getLotteryHistory(lotteryId, pageSize, page);
        if(EmptyUtil.isNotEmpty(resultLotteryType.getResult())){
            return JsonResult.success(resultLotteryType.getResult());
        }
        return JsonResult.success();
    }
}
