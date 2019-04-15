package com.common.weather.free;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class FreeData {
    @JSONField(name = "day")
    private String day;//日期
    @JSONField(name = "date")
    private String date;//日期
    @JSONField(name = "week")
    private String week;//周几
    @JSONField(name = "wea")
    private String wea;//天气
    @JSONField(name = "wea_img")
    private String weaImg;//天气图片
    @JSONField(name = "air")
    private String air;//空气指数
    @JSONField(name = "humidity")
    private String humidity;
    @JSONField(name = "air_level")
    private String airLevel;//空气质量等级
    @JSONField(name = "air_tips")
    private String airTips;//空气质量描述
    @JSONField(name = "alarm")
    private FreeAlarm alarm;
    @JSONField(name = "tem1")
    private String tem1;//温度
    @JSONField(name = "tem2")
    private String tem2;//温度
    @JSONField(name = "tem")
    private String tem;//温度
    @JSONField(name = "win")
    private String[] win;//风力方向
    @JSONField(name = "win_speed")
    private String winSpeed;//风力/
    @JSONField(name = "hours")
    private List<FreeHours> hours;//分时天气
    @JSONField(name = "index")
    private List<FreeIndex> index;//各种指数

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAirLevel() {
        return airLevel;
    }

    public void setAirLevel(String airLevel) {
        this.airLevel = airLevel;
    }

    public String getAirTips() {
        return airTips;
    }

    public void setAirTips(String airTips) {
        this.airTips = airTips;
    }

    public FreeAlarm getAlarm() {
        return alarm;
    }

    public void setAlarm(FreeAlarm alarm) {
        this.alarm = alarm;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String[] getWin() {
        return win;
    }

    public void setWin(String[] win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }

    public List<FreeHours> getHours() {
        return hours;
    }

    public void setHours(List<FreeHours> hours) {
        this.hours = hours;
    }

    public List<FreeIndex> getIndex() {
        return index;
    }

    public void setIndex(List<FreeIndex> index) {
        this.index = index;
    }
}
