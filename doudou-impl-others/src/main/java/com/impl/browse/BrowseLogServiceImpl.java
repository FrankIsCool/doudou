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
        JsonResult<Integer> result = new JsonResult<>();
        browseLogMapper.save(browseLog);

        return result;
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
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(browseLogMapper.getCountByDay(date));
        return result;
    }

    @Override
    public JsonResult<Integer> getUserCounByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(browseLogMapper.getUserCounByDay(date));
        return result;
    }

    @Override
    public JsonResult<List<Long>> getUserIdsByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<List<Long>> result = new JsonResult<>();
        result.setData(browseLogMapper.getUserIdsByDay(date));
        return result;
    }

    @Override
    public JsonResult<List<BrowseLog>> getBrowseLogsByDayUserId(String date, long userId) {
        if(EmptyUtil.isEmpty(date)||EmptyUtil.isEmpty(userId)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<List<BrowseLog>> result = new JsonResult<>();
        result.setData(browseLogMapper.getBrowseLogsByDayUserId(date,userId));
        return result;
    }

    @Override
    public JsonResult<Integer> getUrlCountByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<Integer> result = new JsonResult<>();
        result.setData(browseLogMapper.getUrlCountByDay(date));
        return result;
    }

    @Override
    public JsonResult<List<String>> getUrlsByDay(String date) {
        if(EmptyUtil.isEmpty(date)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<List<String>> result = new JsonResult<>();
        result.setData(browseLogMapper.getUrlsByDay(date));
        return result;
    }

    @Override
    public JsonResult<List<BrowseLog>> getBrowseLogsByDayUrl(String date, String url) {
        if(EmptyUtil.isEmpty(date)||EmptyUtil.isEmpty(url)){
            return JsonResult.error(ExceptionCode.ERRO_400);
        }
        JsonResult<List<BrowseLog>> result = new JsonResult<>();
        result.setData(browseLogMapper.getBrowseLogsByDayUrl(date,url));
        return result;
    }

}
