package com.impl.browse;

import com.alibaba.dubbo.config.annotation.Service;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.impl.dao.slaver.browse.BrowseLogMapper;
import com.service.browse.BrowseLogService;
import com.service.model.BrowseLog;
import org.springframework.beans.factory.annotation.Autowired;

@Service
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
}
