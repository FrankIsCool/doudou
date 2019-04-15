package com.common.ip.bj;

import com.alibaba.fastjson.annotation.JSONField;

public class BJData {
    @JSONField(name="country")
    private String country;//国家
    @JSONField(name="country_id")
    private String countryId;//国家简称
    @JSONField(name="area")
    private String area;//区域
    @JSONField(name="area_id")
    private String area_id;//区域id
    @JSONField(name="province")
    private String province;//省份
    @JSONField(name="province_id")
    private String provinceId;//省份id
    @JSONField(name="city")
    private String city;//城市
    @JSONField(name="city_id")
    private String cityId;//城市id
    @JSONField(name="county")
    private String county;//地区
    @JSONField(name="county_id")
    private String countyId;//地区id
    @JSONField(name="isp")
    private String isp;//运营商
    @JSONField(name="isp_id")
    private String isp_id;//运营商id
    @JSONField(name="ip")
    private String ip;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIsp_id() {
        return isp_id;
    }

    public void setIsp_id(String isp_id) {
        this.isp_id = isp_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
