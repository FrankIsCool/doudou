package com.common.calendar;

import com.alibaba.fastjson.annotation.JSONField;

public class CalendarData {
    @JSONField(name = "holiday")
    private String holiday;//假日
    @JSONField(name = "avoid")
    private String avoid;//忌
    @JSONField(name = "animalsYear")
    private String animalsYear;//属相
    @JSONField(name = "desc")
    private String desc;//假日描述
    @JSONField(name = "weekday")
    private String weekday;//周几
    @JSONField(name = "suit")
    private String suit;//宜
    @JSONField(name = "lunarYear")
    private String lunarYear;//纪年
    @JSONField(name = "lunar")
    private String lunar;//农历
    @JSONField(name = "year-month")
    private String yearMonth;//年份和月份
    @JSONField(name = "date")
    private String date;//具体日期

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getAnimalsYear() {
        return animalsYear;
    }

    public void setAnimalsYear(String animalsYear) {
        this.animalsYear = animalsYear;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
