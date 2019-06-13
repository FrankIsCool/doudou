package com.common.weather.juhe;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class JHWeatherData {
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "realtime")
    private JHRealTime realtime;
    @JSONField(name = "future")
    private List<JHFuture> futures;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public JHRealTime getRealtime() {
        return realtime;
    }

    public void setRealtime(JHRealTime realtime) {
        this.realtime = realtime;
    }

    public List<JHFuture> getFutures() {
        return futures;
    }

    public void setFutures(List<JHFuture> futures) {
        this.futures = futures;
    }
}
