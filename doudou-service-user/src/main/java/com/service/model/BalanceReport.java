package com.service.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Date;

public class BalanceReport<T> {
    /*
    每周统计
     */
    public static final int REPORT_WEEK = 1;
    /*
    每月统计
     */
    public static final int REPORT_MON = 2;
    /*
    每季度统计
     */
    public static final int REPORT_QUARTER = 3;
    /*
    每年统计
     */
    public static final int REPORT_YEAR = 4;
    //id
    private long id;
    //用户id
    private long userId;
    //资金账户id
    private long balanceId;
    //统计类型1，每周 2，每月 3，每季度 4，每年
    private int type;
    //资金账户
    private String reportTime;
    //统计结果，json字符串
    private String reportResult;
    //创建时间
    private Date createTime;
    //统计结果，实体类
    private T reportResultVo;

    public void setReportResult(String reportResult) {

        Object object = JSONObject.parse(reportResult);
        this.reportResultVo = (T)object;
        this.reportResult = reportResult;
    }

    public T getReportResultVo() {
        return reportResultVo;
    }

    public void setReportResultVo(T reportResultVo) {
        this.reportResult = JSONObject.toJSONString(reportResultVo);
        this.reportResultVo = reportResultVo;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportResult() {
        return reportResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
