package com.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class BalanceBillLog implements Serializable {
    /**
     * 充值
     */
    public static final int  OPERATING_RECHARGE= 1;
    /**
     * 消费
     */
    public static final int  OPERATING_CONSUMPTION= 2;
    /**
     * 冻结
     */
    public static final int  OPERATING_FREEZE= 3;
    /**
     * 解冻
     */
    public static final int  OPERATING_UNFREEZE= 4;

    //id
    private long id;
    //用户id
    private long userId;
    //账户id
    private long balanceId;
    //操作金额
    private BigDecimal balance;
    //操作记录
    private String remark;
    //操作类型：1，充值 2，消费 3，冻结 4，解冻
    private int operating;
    //创建时间
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getOperating() {
        return operating;
    }

    public void setOperating(int operating) {
        this.operating = operating;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
