package com.common.ip.cha;

import com.alibaba.fastjson.annotation.JSONField;

public class ChaResult {

    @JSONField(name="status")
    private String status;
    @JSONField(name="msg")
    private String msg;
    @JSONField(name="data")
    private ChaData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ChaData getData() {
        return data;
    }

    public void setData(ChaData data) {
        this.data = data;
    }
}
