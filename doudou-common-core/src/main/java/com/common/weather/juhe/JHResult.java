package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

public class JHResult<T>{
    @JSONField(name = "reason")
    private String reason ;
    @JSONField(name = "result")
    private T result ;
    @JSONField(name = "error_code")
    private String errorCode ;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
