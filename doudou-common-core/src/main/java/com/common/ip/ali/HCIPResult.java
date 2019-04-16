package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class HCIPResult {
    @JSONField(name="ret")
    private String ret;
    @JSONField(name="msg")
    private String msg;
    @JSONField(name="log_id")
    private String logId;
    @JSONField(name="data")
    private HCIPData data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public HCIPData getData() {
        return data;
    }

    public void setData(HCIPData data) {
        this.data = data;
    }
}
