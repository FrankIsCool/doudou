package com.service.browse;

import com.common.jsonResult.JsonResult;
import com.service.model.BrowseLog;

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
}
