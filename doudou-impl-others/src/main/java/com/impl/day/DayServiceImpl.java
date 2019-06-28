package com.impl.day;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.calendar.CalendarData;
import com.common.calendar.CalendarUtil;
import com.common.empty.EmptyUtil;
import com.common.jsonResult.JsonResult;
import com.common.weather.juhe.JHResult;
import com.service.day.DayService;
import com.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private RedisService redisService;

    @Override
    public JsonResult<CalendarData> getDayInfo(String day) {
        CalendarData calendarData = (CalendarData) redisService.getObject(day);
        if(EmptyUtil.isNotEmpty(calendarData)){
            return JsonResult.success(calendarData);
        }
        JHResult<CalendarData> jhResult = CalendarUtil.getCalendar(day);
        if(EmptyUtil.isEmpty(jhResult.getResult())){
            return JsonResult.success();
        }
        redisService.set(day, jhResult.getResult());
        return JsonResult.success(jhResult.getResult());
    }
}
