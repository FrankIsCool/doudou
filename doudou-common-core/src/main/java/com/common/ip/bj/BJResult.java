package com.common.ip.bj;

import com.alibaba.fastjson.annotation.JSONField;

public class BJResult {
    @JSONField(name="status")
    private String status;
    @JSONField(name="data")
    private BJData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BJData getData() {
        return data;
    }

    public void setData(BJData data) {
        this.data = data;
    }
}
