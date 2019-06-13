package com.common.lottery;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class LotteryPrize  implements Serializable {
    @JSONField(name = "prize_name")
    private String prizeName;//奖项名称
    @JSONField(name = "prize_num")
    private String prize_num;//中奖数量
    @JSONField(name = "prize_amount")
    private String prize_amount;//中奖金额
    @JSONField(name = "prize_require")
    private String prize_require;//中奖条件

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrize_num() {
        return prize_num;
    }

    public void setPrize_num(String prize_num) {
        this.prize_num = prize_num;
    }

    public String getPrize_amount() {
        return prize_amount;
    }

    public void setPrize_amount(String prize_amount) {
        this.prize_amount = prize_amount;
    }

    public String getPrize_require() {
        return prize_require;
    }

    public void setPrize_require(String prize_require) {
        this.prize_require = prize_require;
    }
}
