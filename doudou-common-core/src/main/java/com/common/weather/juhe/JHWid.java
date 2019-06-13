package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

public class JHWid {
    @JSONField(name = "day")
    private String day;
    @JSONField(name = "night")
    private String night;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }
}
