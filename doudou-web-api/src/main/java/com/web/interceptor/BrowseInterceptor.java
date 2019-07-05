package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.browse.BrowseLogService;
import com.service.model.BrowseLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class BrowseInterceptor implements HandlerInterceptor {
    private static final Logger log = Logger.getLogger("browse");
    @Autowired
    private BrowseLogService browseLogService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取值
        String source = request.getHeader("source");
        String url = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String token = request.getHeader("token");
        Integer userId = 0;
        if(EmptyUtil.isNotEmpty(token)){
            TokenVo tokenVo = TokenUtil.getTokenVo(token);
            userId = Integer.valueOf(tokenVo.getUserId());
        }
        if(EmptyUtil.isEmpty(source)){
            source = "1";
        }
        //保存数据库（浏览记录）
        BrowseLog browseLog = new BrowseLog();
        browseLog.setSource(Integer.valueOf(source));
        if(EmptyUtil.isNotEmpty(parameterMap)){
            browseLog.setParam(JSONObject.toJSONString(parameterMap));
        }
        browseLog.setUserId(userId);
        browseLog.setUrl(url);
        browseLog.setCreateTime(new Date());
        browseLogService.save(browseLog);
        //打印日志
        log.info(JSONObject.toJSONString(browseLog));
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
