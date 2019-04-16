package com.common.ip.ali;

import com.alibaba.fastjson.annotation.JSONField;

public class FSIPData {
    @JSONField(name="country")
    private String country;//国家
    @JSONField(name="country_code")
    private String countryCode;//国家英文简称
    @JSONField(name="big_area")
    private String bigArea;//大区
    @JSONField(name="prov")
    private String prov;//省份
    @JSONField(name="city")
    private String city;//市
    @JSONField(name="city_code")
    private String cityCode;//城市编码
    @JSONField(name="city_short_code")
    private String cityShortCode;//城市英文简称
    @JSONField(name="area")
    private String area;//区或者县
    @JSONField(name="area_code")
    private String areaCode;//区或者县
    @JSONField(name="isp")
    private String isp;//运营商

    @JSONField(name="post_code")
    private String postCode;//国家英文简称
    @JSONField(name="lng")
    private String lng;
    @JSONField(name="lat")
    private String lat;
    @JSONField(name="long_ip")
    private String longIp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getBigArea() {
        return bigArea;
    }

    public void setBigArea(String bigArea) {
        this.bigArea = bigArea;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityShortCode() {
        return cityShortCode;
    }

    public void setCityShortCode(String cityShortCode) {
        this.cityShortCode = cityShortCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongIp() {
        return longIp;
    }

    public void setLongIp(String longIp) {
        this.longIp = longIp;
    }
}
