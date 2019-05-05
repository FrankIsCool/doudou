package com.impl.day;

import com.common.calendar.CalendarData;
import com.common.calendar.CalendarUtil;
import com.common.empty.EmptyUtil;
import com.common.jsonResult.JsonResult;
import com.common.weather.juhe.JHResult;
import com.service.day.DayService;
import com.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

public class DayServiceImpl implements DayService {

    @Autowired
    private RedisService redisService;

    @Override
    public JsonResult<CalendarData> getDayInfo(String day) {
        JsonResult<CalendarData> result = new JsonResult<>();
        CalendarData calendarData = (CalendarData) redisService.getObject(day);
        if(EmptyUtil.isNotEmpty(calendarData)){
            result.setData(calendarData);
            return result;
        }
        JHResult<CalendarData> jhResult = CalendarUtil.getCalendar(day);
        if(EmptyUtil.isNotEmpty(jhResult.getResult())){
            redisService.set(day, jhResult.getResult());
            result.setData(jhResult.getResult());
        }
        return result;
    }
}
