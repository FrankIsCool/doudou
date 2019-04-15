package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 	聚合数据
 * 	当前天气详情情况
 */
public class JHRealTime {
    @JSONField(name = "temperature")
    private String temperature ;//温度，可能为空
    @JSONField(name = "humidity")
    private String humidity ;//湿度，可能为空
    @JSONField(name = "info")
    private String info ;//	天气情况
    @JSONField(name = "wid")
    private String wid ;//天气标识id，可参考小接口2
    @JSONField(name = "direct")
    private String direct ;//	风向，可能为空
    @JSONField(name = "power")
    private String power ;//	风力，可能为空
    @JSONField(name = "aqi")
    private String aqi ;//	空气质量指数，可能为空

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
}
