package com.common.weather.free;

import com.alibaba.fastjson.annotation.JSONField;

public class FreeAlarm {
    @JSONField(name = "alarm_type")
    private String alarmType;
    @JSONField(name = "alarm_level")
    private String alarmLevel;
    @JSONField(name = "alarm_content")
    private String alarmContent;

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }
}
