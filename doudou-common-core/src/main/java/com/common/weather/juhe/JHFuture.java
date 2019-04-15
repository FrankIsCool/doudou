package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 	近5天天气情况
 */
public class JHFuture {
    @JSONField(name = "date")
    private String date;//	日期
    @JSONField(name = "temperature")
    private String temperature;//		温度，最低温/最高温
    @JSONField(name = "weather")
    private String weather;//	天气情况
    @JSONField(name = "direct")
    private String direct;//		风向
    @JSONField(name = "wid")
    private JHWid wid;//	日期

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public JHWid getWid() {
        return wid;
    }

    public void setWid(JHWid wid) {
        this.wid = wid;
    }
}
