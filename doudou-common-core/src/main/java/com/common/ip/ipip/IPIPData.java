package com.common.ip.ipip;

import com.alibaba.fastjson.annotation.JSONField;

public class IPIPData {
    @JSONField(name = "country")
    private String country;//国家
    @JSONField(name = "province")
    private String province;//省
    @JSONField(name = "city")
    private String city;//市
    @JSONField(name = "district")
    private String district;//区或者县
    @JSONField(name = "isp")
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
