package com.common.ip.free;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 免费ip识别地址
 * https://www.kancloud.cn/ccjin/yingq/875681
 */
public class FreeAddress {
    @JSONField(name="ip")
    private String ip;
    @JSONField(name="country")
    private String country;//国家
    @JSONField(name="province")
    private String province;//省
    @JSONField(name="city")
    private String city;//城市
    @JSONField(name="isp")
    private String isp;//运营商

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

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
