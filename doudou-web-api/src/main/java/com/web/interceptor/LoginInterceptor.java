package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import com.common.token.TokenVo;
import com.service.model.UserLogin;
import com.service.model.UserNode;
import com.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String token = request.getHeader("token");
        if(EmptyUtil.isEmpty(token)){
            retrunErro(response,JsonResult.error(ExceptionCode.ERRO_100001));
            return false;
        }
        String source = request.getHeader("source");
        if(EmptyUtil.isEmpty(source)){
            retrunErro(response,JsonResult.error(ExceptionCode.ERRO_100002));
            return false;
        }
        if(!TokenUtil.loginVerifyToken(token, source)){
            retrunErro(response,JsonResult.error(ExceptionCode.ERRO_100003));
            return false;
        }
        TokenVo tokenVo = TokenUtil.getTokenVo(token);
        Object object = redisService.getObject(tokenVo.getUserCode());
        UserLogin userLogin = new UserLogin();
        if(object instanceof Map){
            BeanMap.create(userLogin).putAll((Map) object);
        }else {
            userLogin = (UserLogin)object;
        }
        if(!token.equals(userLogin.getToken())){
            retrunErro(response,JsonResult.error(ExceptionCode.ERRO_100003));
            return false;
        }
        if(!UserNode.USER_STATE_NORMAL.equals(userLogin.getState())){
            retrunErro(response,JsonResult.error(ExceptionCode.ERRO_100003));
            return false;
        }
        return true;
    }
    private void retrunErro(HttpServletResponse response,JsonResult jsonResult){
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(JSONObject.toJSON(jsonResult));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
