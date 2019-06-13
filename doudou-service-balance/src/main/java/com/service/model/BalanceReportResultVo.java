package com.service.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceReportResultVo implements Serializable {
    //第几周
    private int num;
    //充值金额
    private BigDecimal recharge;
    //消费金额
    private BigDecimal consumption;
    //账户剩余可用金额
    private BigDecimal balance;
    //账户剩余冻结金额
    private BigDecimal freeze;
    //账户剩余总金额
    private BigDecimal totalBalance;
    //当前对象加密串
    private String md5;

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

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
