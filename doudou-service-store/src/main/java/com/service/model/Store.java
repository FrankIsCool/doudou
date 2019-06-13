package com.service.model;

import java.sql.Date;

public class Store {

    /**
     * 申请
     */
    public static final int  STATE_APPLY= 1;
    /**
     * 正常
     */
    public static final int  STATE_NORMAL= 2;
    /**
     * 异常
     */
    public static final int  STATE_ABNORMAL= 3;
    /**
     * 冻结
     */
    public static final int  STATE_FREEZE= 4;
    /**
     * 审核不通过
     */
    public static final int  STATE_AUDIT_FAILED= 5;
    //id
    private long id;
    //创建人id
    private long userId;
    //店铺名称
    private String name;
    //店铺状态 1：申请 2：正常  :3：异常  4：冻结  5：审核不通过
    private int state;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
