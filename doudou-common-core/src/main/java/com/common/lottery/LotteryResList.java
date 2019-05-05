package com.common.lottery;

import com.alibaba.fastjson.annotation.JSONField;

public class LotteryResList {
    @JSONField(name = "lottery_id")
    private String lotteryId;//		彩票ID
    @JSONField(name = "lottery_res")
    private String lotteryRes;//	开奖结果
    @JSONField(name = "lottery_no")
    private String lotteryNo;//		开奖期号
    @JSONField(name = "lottery_date")
    private String lotteryDate;//		开奖日期
    @JSONField(name = "lottery_exdate")
    private String lotteryExdate;//		兑奖截止日期
    @JSONField(name = "lottery_sale_amount")
    private String lotterySaleAmount;//		本期销售额，可能为空
    @JSONField(name = "lottery_pool_amount")
    private String lotteryPoolAmount;//		奖池滚存，可能为空

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
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
}
