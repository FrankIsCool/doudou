package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class YYIPData {
    @JSONField(name="ret_code")
    private int retCode;//
    @JSONField(name="continents")
    private String continents;//洲际
    @JSONField(name="country")
    private String country;//国家
    @JSONField(name="region")
    private String region;//省份
    @JSONField(name="city")
    private String city;//市
    @JSONField(name="county")
    private String county;//区或者县
    @JSONField(name="isp")
    private String isp;//运营商
    @JSONField(name="city_code")
    private String cityCode;//城市编码
    @JSONField(name="en_name")
    private String enName;//国家英文
    @JSONField(name="en_name_short")
    private String enNameShort;//国家英文简称
    @JSONField(name="lnt")
    private String lnt;
    @JSONField(name="lat")
    private String lat;
    @JSONField(name="ip")
    private String ip;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getContinents() {
        return continents;
    }

    public void setContinents(String continents) {
        this.continents = continents;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnNameShort() {
        return enNameShort;
    }

    public void setEnNameShort(String enNameShort) {
        this.enNameShort = enNameShort;
    }

    public String getLnt() {
        return lnt;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
