package com.service.model;

import java.io.Serializable;
import java.sql.Date;

public class Label implements Serializable {

    /**
     * 正常
     */
    public static final int  STATE_NORMAL= 1;
    /**
     * 删除
     */
    public static final int  STATE_DELETE= 2;
    //id
    private long id;
    //标签名称
    private String label;
    //备注
    private String remark;
    //账户状态：1正常  2异常  3冻结
    private int state;
    //创建时间
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
