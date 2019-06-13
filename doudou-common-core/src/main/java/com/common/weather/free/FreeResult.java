package com.common.weather.free;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class FreeResult {
    @JSONField(name = "cityid")
    private String cityid;
    @JSONField(name = "update_time")
    private String updateTime;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "cityEn")
    private String cityEn;
    @JSONField(name = "country")
    private String country;
    @JSONField(name = "countryEn")
    private String countryEn;
    @JSONField(name = "data")
    private List<FreeData> datas;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public List<FreeData> getDatas() {
        return datas;
    }

    public void setDatas(List<FreeData> datas) {
        this.datas = datas;
    }
}
