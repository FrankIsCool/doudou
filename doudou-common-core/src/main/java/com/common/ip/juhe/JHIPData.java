package com.common.ip.juhe;

import com.alibaba.fastjson.annotation.JSONField;

public class JHIPData {
    @JSONField(name = "Country")
    private String country;//国家
    @JSONField(name = "Province")
    private String province;//省
    @JSONField(name = "City")
    private String city;//市
    @JSONField(name = "Isp")
    private String isp;//运营商

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
