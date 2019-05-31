package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.empty.EmptyUtil;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import com.common.token.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
