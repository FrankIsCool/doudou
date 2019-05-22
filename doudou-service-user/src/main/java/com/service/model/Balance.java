package com.service.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Balance implements Serializable {
    /**
     * 正常
     */
    public static final int  STATE_NORMAL= 1;
    /**
     * 异常
     */
    public static final int  STATE_ABNORMAL= 2;
    /**
     * 冻结
     */
    public static final int  STATE_FREEZE= 3;
    //id
    private int id;
    //用户id
    private long userId;
    //可用余额
    private BigDecimal balance;
    //冻结金额
    private BigDecimal freeze;
    //总余额
    private BigDecimal totalBalance;
    //余额加密串
    private String md5;
    //账户状态：1正常  2异常  3冻结
    private int state;
    //创建时间
    private Date createTime;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
