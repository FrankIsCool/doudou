package com.web.interceptor;

import com.common.empty.EmptyUtil;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.browse.BrowseLogService;
import com.service.model.BrowseLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class BrowseInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BrowseLogService browseLogService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("------------------------------------");
        String source = request.getHeader("source");
        String url = request.getRequestURI();
        String token = request.getHeader("token");
        Integer userId = 0;
        if(EmptyUtil.isNotEmpty(token)){
            TokenVo tokenVo = TokenUtil.getTokenVo(token);
            userId = Integer.valueOf(tokenVo.getUserId());
        }
        if(EmptyUtil.isEmpty(source)){
            source = "1";
        }
        BrowseLog browseLog = new BrowseLog();
        browseLog.setSource(Integer.valueOf(source));
        browseLog.setUserId(userId);
        browseLog.setUrl(url);
        browseLog.setCreateTime(new Date());
        browseLogService.save(browseLog);
//        browseLogService.save(url,userId,Integer.valueOf(source),null,null);
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
