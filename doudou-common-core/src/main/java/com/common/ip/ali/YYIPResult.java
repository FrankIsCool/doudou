package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class YYIPResult {
    @JSONField(name="showapi_res_error")
    private String showapiResError;
    @JSONField(name="showapi_res_id")
    private String showapiResId;
    @JSONField(name="showapi_res_code")
    private String showapiResCode;
    @JSONField(name="showapi_res_body")
    private YYIPData showapiResBody;

    public String getShowapiResError() {
        return showapiResError;
    }

    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }

    public String getShowapiResId() {
        return showapiResId;
    }

    public void setShowapiResId(String showapiResId) {
        this.showapiResId = showapiResId;
    }

    public String getShowapiResCode() {
        return showapiResCode;
    }

    public void setShowapiResCode(String showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    public YYIPData getShowapiResBody() {
        return showapiResBody;
    }

    public void setShowapiResBody(YYIPData showapiResBody) {
        this.showapiResBody = showapiResBody;
    }
}
