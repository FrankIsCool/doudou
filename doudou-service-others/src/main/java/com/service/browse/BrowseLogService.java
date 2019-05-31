package com.service.browse;

import com.common.jsonResult.JsonResult;
import com.service.model.BrowseLog;

import java.util.List;

public interface BrowseLogService {
    /**
     * 保存浏览记录
     * @param browseLog     浏览记录实体类
     * @return
     */
    JsonResult<Integer> save(BrowseLog browseLog);

    /**
     * 保存浏览记录
     * @param url           浏览url
     * @param userId        用户id
     * @param source        用户登录源
     * @param otherInfo     其他信息，json字符串
     * @param deviceInfo    设备信息，json字符串
     * @return
     */
    JsonResult<Integer> save(String url,int userId,int source,String otherInfo,String deviceInfo);

    /**
     * 获取某天有多少次浏览
     * @param date
     * @return
     */
    JsonResult<Integer> getCountByDay(String date);
    /**
     * 获取某天有多少人次访问
     * @param date
     * @return
     */
    JsonResult<Integer> getUserCounByDay(String date);
    /**
     * 获取某天访问人的id
     * @param date
     * @return
     */
    JsonResult<List<Long>> getUserIdsByDay(String date);
    /**
     * 获取某天某个用户的访问情况
     * @param date
     * @param userId
     * @return
     */
    JsonResult<List<BrowseLog>> getBrowseLogsByDayUserId(String date,long userId);
    /**
     * 获取某天有多少功能被访问
     * @param date
     * @return
     */
    JsonResult<Integer> getUrlCountByDay(String date);
    /**
     * 获取某天被访问的功能都有哪些
     * @param date
     * @return
     */
    JsonResult<List<String>> getUrlsByDay(String date);
    /**
     * 获取某天某个功能的访问情况
     * @param date
     * @param url
     * @return
     */
    JsonResult<List<BrowseLog>> getBrowseLogsByDayUrl(String date,String url);
}
