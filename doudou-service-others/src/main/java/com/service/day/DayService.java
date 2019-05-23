package com.service.day;


import com.common.calendar.CalendarData;
import com.common.jsonResult.JsonResult;

public interface DayService {
    /**
     * 获取某天的详情
     * @param day   天，例如：2012-1-1
     * @return
     */
    JsonResult<CalendarData> getDayInfo(String day);
}
