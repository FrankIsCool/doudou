package com.web.controller.day;

import com.common.calendar.CalendarData;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.service.day.DayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static jdk.nashorn.internal.objects.NativeString.match;

@RestController
@RequestMapping(value = "v1/day")
@Api(value="DayController",tags={"日历"})
public class DayController {
    @Autowired
    public DayService dayService;

    @RequestMapping(value ="/info", method= RequestMethod.GET)
    @ApiOperation(value="日期详情")
    @ApiImplicitParam(paramType="query", name = "day", value = "日期：2012-1-1", required = true, dataType = "String")
    public JsonResult<CalendarData> getDayInfo(String day){
        if(EmptyUtil.isEmpty(day)){
            return JsonResult.error(ExceptionCode.ERRO_100000);
        }
        String[] days = day.split("-");
        if(days.length!=3){
            return JsonResult.error(ExceptionCode.ERRO_100004);
        }
        if(0>Integer.valueOf(days[0])||Integer.valueOf(days[0])>9999){
            return JsonResult.error(ExceptionCode.ERRO_100004);
        }
        if(1>Integer.valueOf(days[1])||Integer.valueOf(days[1])>12){
            return JsonResult.error(ExceptionCode.ERRO_100004);
        }
        if(1>Integer.valueOf(days[2])||Integer.valueOf(days[2])>31){
            return JsonResult.error(ExceptionCode.ERRO_100004);
        }
        String regex  = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((([13578]|1[02])-([12][0-9]|3[01]|[1-9]))|(([469]|11)-([12][0-9]|30|[1-9]))|(2-([[1][0-9]|2[0-8]|[1-9])))";
        if(EmptyUtil.isEmpty(match(regex, day))){
            return JsonResult.error(ExceptionCode.ERRO_100004);
        }
        return dayService.getDayInfo(day);
    }
}
