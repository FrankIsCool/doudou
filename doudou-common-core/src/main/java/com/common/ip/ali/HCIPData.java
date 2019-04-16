package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class HCIPData {
    @JSONField(name="area")
    private String area;//大区域
    @JSONField(name="country")
    private String country;//国家
    @JSONField(name="region_id")
    private String regionId;//省份id
    @JSONField(name="region")
    private String region;//省份
    @JSONField(name="city")
    private String city;//市
    @JSONField(name="isp")
    private String isp;//运营商
    @JSONField(name="long_ip")
    private String longIp;
    @JSONField(name="ip")
    private String ip;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getLongIp() {
        return longIp;
    }

    public void setLongIp(String longIp) {
        this.longIp = longIp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
