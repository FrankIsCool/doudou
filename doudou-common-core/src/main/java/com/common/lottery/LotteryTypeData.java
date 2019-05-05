package com.common.lottery;

import com.alibaba.fastjson.annotation.JSONField;

public class LotteryTypeData {

    @JSONField(name = "lottery_id")
    private String lotteryId;//彩票编码
    @JSONField(name = "lottery_name")
    private String lotteryName;//彩票名称
    @JSONField(name = "lottery_type_id")
    private String lotteryTypeId;//彩票类型id
    @JSONField(name = "remarks")
    private String remarks;//备注

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

    public String getLotteryTypeId() {
        return lotteryTypeId;
    }

    public void setLotteryTypeId(String lotteryTypeId) {
        this.lotteryTypeId = lotteryTypeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
