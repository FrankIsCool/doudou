package com.common.calendar;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;
import com.common.weather.juhe.JHResult;

import java.util.HashMap;
import java.util.Map;

public class CalendarUtil {

    private static String URL = "http://v.juhe.cn/calendar/day";

    private static String KEY = "ff0e8a51018cda1a148d3d616d084e30";
    /**
     * 获取日历详情
     * @param day 2012-1-1
     * @return
     */
    public static JHResult<CalendarData> getCalendar(String day){
        Map params = new HashMap();
        params.put("day",day);
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL, params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        CalendarData calendarData = JSONObject.parseObject(jhResult.getResult().toString(), CalendarData.class);
        jhResult.setResult(calendarData);
        return jhResult;
    }

}
