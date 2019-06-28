package com.impl.browse;

import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.slaver.browse.BrowseLogMapper;
import com.service.browse.BrowseLogService;
import com.service.model.BrowseLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service
@org.springframework.stereotype.Service
public class BrowseLogServiceImpl implements BrowseLogService {
    @Autowired
    public BrowseLogMapper browseLogMapper;
    @Override
    public JsonResult<Integer> save(BrowseLog browseLog) {
        if(EmptyUtil.isEmpty(browseLog)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return  JsonResult.success(browseLogMapper.save(browseLog));
    }

    @Override
    public JsonResult<Integer> save(String url, int userId, int source,String otherInfo,String deviceInfo) {
        BrowseLog browseLog = new BrowseLog();
        browseLog.setSource(source);
        browseLog.setUserId(userId);
        browseLog.setUrl(url);
        browseLog.setDeviceInfo(deviceInfo);
        browseLog.setOtherInfo(otherInfo);
        return save(browseLog);
    }

    @Override
    public JsonResult<Integer> getCountByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getCountByDay(date));
    }

    @Override
    public JsonResult<Integer> getUserCounByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getUserCounByDay(date));
    }

    @Override
    public JsonResult<List<Long>> getUserIdsByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getUserIdsByDay(date));
    }

    @Override
    public JsonResult<List<BrowseLog>> getBrowseLogsByDayUserId(String date, long userId) {
        if(EmptyUtil.isEmpty(date)||EmptyUtil.isEmpty(userId)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getBrowseLogsByDayUserId(date,userId));
    }

    @Override
    public JsonResult<Integer> getUrlCountByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getUrlCountByDay(date));
    }

    @Override
    public JsonResult<List<String>> getUrlsByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getUrlsByDay(date));
    }

    @Override
    public JsonResult<List<BrowseLog>> getBrowseLogsByDayUrl(String date, String url) {
        if(EmptyUtil.isEmpty(date)||EmptyUtil.isEmpty(url)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        return JsonResult.success(browseLogMapper.getBrowseLogsByDayUrl(date,url));
    }

}
