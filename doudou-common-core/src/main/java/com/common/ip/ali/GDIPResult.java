package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class GDIPResult {
    @JSONField(name="status")
    private String status;
    @JSONField(name="info")
    private String info;
    @JSONField(name="infocode")
    private String infocode;
    @JSONField(name="province")
    private String province;
    @JSONField(name="city")
    private String city;
    @JSONField(name="adcode")
    private String adcode;
    @JSONField(name="rectangle")
    private String rectangle;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getRectangle() {
        return rectangle;
    }

    public void setRectangle(String rectangle) {
        this.rectangle = rectangle;
    }
}
