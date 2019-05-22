package com.service.browse;

import com.common.jsonResult.JsonResult;
import com.service.model.BrowseLog;

public interface BrowseLogService {

    JsonResult<Integer> save(BrowseLog browseLog);

    JsonResult<Integer> save(String url,int userId,int source,String otherInfo,String deviceInfo);
}
