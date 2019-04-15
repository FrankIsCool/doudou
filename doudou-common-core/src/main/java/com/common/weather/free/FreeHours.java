package com.common.weather.free;

import com.alibaba.fastjson.annotation.JSONField;

public class FreeHours {
    @JSONField(name = "day")
    private String day;
    @JSONField(name = "wea")
    private String wea;
    @JSONField(name = "tem")
    private String tem;
    @JSONField(name = "win")
    private String win;
    @JSONField(name = "win_speed")
    private String winSpeed;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWinSpeed() {
        return winSpeed;
    }

    public void setWinSpeed(String winSpeed) {
        this.winSpeed = winSpeed;
    }
}
