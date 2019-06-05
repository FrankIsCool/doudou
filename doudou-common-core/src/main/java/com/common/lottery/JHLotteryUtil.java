package com.common.lottery;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;
import com.common.http.HttpUtil;
import com.common.weather.juhe.JHResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JHLotteryUtil {

    private static String URL = "http://apis.juhe.cn/lottery";

    private static String KEY = "3363396360099e2d01d809b93e51d3f9";
    /**
     * 获取支持的彩票类型
     * @return
     */
    public static JHResult<LotteryTypeData> getLotteryType(){
        Map params = new HashMap();
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL+"/types", params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        List<LotteryTypeData> lotteryTypeData = JSONObject.parseArray(jhResult.getResult().toString(), LotteryTypeData.class);
        jhResult.setResult(lotteryTypeData);
        return jhResult;
    }

    /**
     * 获取彩票中奖结果
     * @param lotteryId 彩票类型编码
     * @param lotteryNo 开奖期号
     * @return
     */
    public static JHResult<LotteryResultData> getLotteryResult(String lotteryId,String lotteryNo){
        Map params = new HashMap();
        params.put("key",KEY);
        params.put("lottery_id",lotteryId);
        if(EmptyUtil.isNotEmpty(lotteryNo)){
            params.put("lottery_no",lotteryNo);
        }
        String s = HttpUtil.doGet(URL+"/query", params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        LotteryResultData lotteryResultData = JSONObject.parseObject(jhResult.getResult().toString(), LotteryResultData.class);
        jhResult.setResult(lotteryResultData);
        return jhResult;
    }

    /**
     * 获取彩票历史结果
     * @param lotteryId 彩票类型编码
     * @param pageSize  每页大小
     * @param page      当前页
     * @return
     */
    public static JHResult<LotteryHistoryData> getLotteryHistory(String lotteryId,int pageSize,int page){
        Map params = new HashMap();
        params.put("key",KEY);
        params.put("page_size",pageSize);
        params.put("page",page);
        String s = HttpUtil.doGet(URL+"/history", params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        LotteryHistoryData lotteryHistoryData = JSONObject.parseObject(jhResult.getResult().toString(), LotteryHistoryData.class);
        jhResult.setResult(lotteryHistoryData);
        return jhResult;
    }
}
