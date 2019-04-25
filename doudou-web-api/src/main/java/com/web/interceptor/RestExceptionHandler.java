package com.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.exception.ExceptionCode;
import com.common.jsonResult.JsonResult;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestControllerAdvice
public class RestExceptionHandler {

//	//运行时异常
//    @ExceptionHandler(RuntimeException.class)  
//    @ResponseBody  
//    public String runtimeExceptionHandler(RuntimeException runtimeException) {  
//
//        return ReturnFormat.retParam(1000, null);
//    }  
//
//    //空指针异常
//    @ExceptionHandler(NullPointerException.class)  
//    @ResponseBody  
//    public String nullPointerExceptionHandler(NullPointerException ex) {  
//        ex.printStackTrace();
//        return ReturnFormat.retParam(1001, null);
//    }   
//    //类型转换异常
//    @ExceptionHandler(ClassCastException.class)  
//    @ResponseBody  
//    public String classCastExceptionHandler(ClassCastException ex) {  
//        ex.printStackTrace();
//        return ReturnFormat.retParam(1002, null);  
//    }  
//
//    //IO异常
//    @ExceptionHandler(IOException.class)  
//    @ResponseBody  
//    public String iOExceptionHandler(IOException ex) {  
//        ex.printStackTrace();
//        return ReturnFormat.retParam(1003, null); 
//    }
	public void sendException(HttpServletResponse response,JsonResult jsonResult){
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
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public void noSuchMethodExceptionHandler(NoSuchMethodException ex, HttpServletResponse response) {
		sendException(response,JsonResult.error(ExceptionCode.ERRO_404));
    }
//
//    //数组越界异常
//    @ExceptionHandler(IndexOutOfBoundsException.class)  
//    @ResponseBody  
//    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {  
//        ex.printStackTrace();
//        return ReturnFormat.retParam(1005, null);
//    }
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public void requestNotReadable(HttpMessageNotReadableException ex, HttpServletResponse response){
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400));
    }
    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public void requestTypeMismatch(TypeMismatchException ex, HttpServletResponse response){
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400));
    }
    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public void requestMissingServletRequest(MissingServletRequestParameterException ex, HttpServletResponse response){
		sendException(response,JsonResult.error(ExceptionCode.ERRO_400));
    }
    //500错误
    @ExceptionHandler({NullPointerException.class})
    public JsonResult requestNullPointer(NullPointerException ex, HttpServletResponse response){
        return JsonResult.error(ExceptionCode.ERRO_500,ex.toString());
    }
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public void request405(HttpRequestMethodNotSupportedException ex, HttpServletResponse response){
		sendException(response,JsonResult.error(ExceptionCode.ERRO_405));
    }
    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public void request406(HttpMediaTypeNotAcceptableException ex, HttpServletResponse response){
		sendException(response,JsonResult.error(ExceptionCode.ERRO_406));
    }
    //404错误--NoHandlerFoundException
    @ExceptionHandler({NoHandlerFoundException.class})
    public JsonResult requestNoHandlerFoundException(){
        return JsonResult.error(ExceptionCode.ERRO_404);
    }
    //500错误
    @ExceptionHandler({Exception.class})
    public JsonResult requestException(Exception ex){
        return JsonResult.error(ExceptionCode.ERRO_500,ex.toString());
    }
}
