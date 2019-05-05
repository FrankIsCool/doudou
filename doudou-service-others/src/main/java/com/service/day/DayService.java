package com.service.day;


import com.common.calendar.CalendarData;
import com.common.jsonResult.JsonResult;

public interface DayService {

    JsonResult<CalendarData> getDayInfo(String day);
}
