package com.common.lottery;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class LotteryResultData {

    @JSONField(name = "lottery_id")
    private String lotteryId;//彩票编码
    @JSONField(name = "lottery_name")
    private String lotteryName;//彩票名称
    @JSONField(name = "lottery_res")
    private String lotteryRes;//开奖结果
    @JSONField(name = "lottery_no")
    private String lotteryNo;//开奖期号
    @JSONField(name = "lottery_date")
    private String lotteryDate;//开奖日期
    @JSONField(name = "lottery_exdate")
    private String lotteryExdate;//兑奖截止日期
    @JSONField(name = "lottery_sale_amount")
    private String lotterySaleAmount;//本期销售额
    @JSONField(name = "lottery_pool_amount")
    private String lotteryPoolAmount;//奖池滚存
    @JSONField(name = "lottery_prize")
    private List<LotteryPrize> lotteryPrize;//开奖详情

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
    }

    public String getLotteryRes() {
        return lotteryRes;
    }

    public void setLotteryRes(String lotteryRes) {
        this.lotteryRes = lotteryRes;
    }

    public String getLotteryNo() {
        return lotteryNo;
    }

    public void setLotteryNo(String lotteryNo) {
        this.lotteryNo = lotteryNo;
    }

    public String getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(String lotteryDate) {
        this.lotteryDate = lotteryDate;
    }

    public String getLotteryExdate() {
        return lotteryExdate;
    }

    public void setLotteryExdate(String lotteryExdate) {
        this.lotteryExdate = lotteryExdate;
    }

    public String getLotterySaleAmount() {
        return lotterySaleAmount;
    }

    public void setLotterySaleAmount(String lotterySaleAmount) {
        this.lotterySaleAmount = lotterySaleAmount;
    }

    public String getLotteryPoolAmount() {
        return lotteryPoolAmount;
    }

    public void setLotteryPoolAmount(String lotteryPoolAmount) {
        this.lotteryPoolAmount = lotteryPoolAmount;
    }

    public List<LotteryPrize> getLotteryPrize() {
        return lotteryPrize;
    }

    public void setLotteryPrize(List<LotteryPrize> lotteryPrize) {
        this.lotteryPrize = lotteryPrize;
    }
}
