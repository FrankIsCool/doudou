package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	public void sendException(HttpServletResponse response,JsonResult jsonResult){
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(JSONObject.toJSON(jsonResult));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            logger.error("set restException is error:",e);
		}
	}
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public void noSuchMethodExceptionHandler(NoSuchMethodException ex, HttpServletResponse response) {
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_404,ex.toString()));
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex, HttpServletResponse response) {
        ex.printStackTrace();
        sendException(response,JsonResult.error(ExceptionCode.ERRO_500,ex.toString()));
    }
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public void requestNotReadable(HttpMessageNotReadableException ex, HttpServletResponse response){
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400,ex.toString()));
    }
    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public void requestTypeMismatch(TypeMismatchException ex, HttpServletResponse response){
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400,ex.toString()));
    }
    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public void requestMissingServletRequest(MissingServletRequestParameterException ex, HttpServletResponse response){
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400,ex.toString()));
    }
    //500错误
    @ExceptionHandler({NullPointerException.class})
    public void requestNullPointer(NullPointerException ex, HttpServletResponse response){
        ex.printStackTrace();
        sendException(response,JsonResult.error(ExceptionCode.ERRO_500,ex.toString()));
    }
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public void request405(HttpRequestMethodNotSupportedException ex, HttpServletResponse response){
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_405,ex.toString()));
    }
    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public void request406(HttpMediaTypeNotAcceptableException ex, HttpServletResponse response){
        ex.printStackTrace();
		sendException(response,JsonResult.error(ExceptionCode.ERRO_406,ex.toString()));
    }
    //404错误--NoHandlerFoundException
    @ExceptionHandler({NoHandlerFoundException.class})
    public void requestNoHandlerFoundException(NoHandlerFoundException ex, HttpServletResponse response){
        ex.printStackTrace();
        sendException(response,JsonResult.error(ExceptionCode.ERRO_404,ex.toString()));
    }
    //500错误
    @ExceptionHandler({Exception.class})
    public void requestException(Exception ex, HttpServletResponse response){
        ex.printStackTrace();
        sendException(response,JsonResult.error(ExceptionCode.ERRO_500,ex.toString()));
    }
}
