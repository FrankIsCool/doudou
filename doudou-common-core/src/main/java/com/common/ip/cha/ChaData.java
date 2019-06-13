package com.common.ip.cha;

import com.alibaba.fastjson.annotation.JSONField;

public class ChaData {
    @JSONField(name="ip")
    private String ip;
    @JSONField(name="country")
    private String country; /*所属国家*/
    @JSONField(name="area")
    private String area;/*所在大的地区*/
    @JSONField(name="province")
    private String province;/*所在的省*/
    @JSONField(name="city")
    private String city;/*所在的城市*/
    @JSONField(name="district")
    private String district;/*所在的区域*/
    @JSONField(name="linetype")
    private String linetype;/*所属运营商*/

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getLinetype() {
        return linetype;
    }

    public void setLinetype(String linetype) {
        this.linetype = linetype;
    }
}
