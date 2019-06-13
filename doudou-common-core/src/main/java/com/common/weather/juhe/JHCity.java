package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

public class JHCity {
    @JSONField(name = "id")
    private String id;//id
    @JSONField(name = "province")
    private String province;//省
    @JSONField(name = "city")
    private String city;//市
    @JSONField(name = "district")
    private String district;//区或者县

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
