package com.common.jsonResult;

import com.common.exception.ExceptionCode;

import java.io.Serializable;
import java.util.List;

public class JsonResult <T> implements Serializable {

    public static final String SUCCESS = "1";
    /**
     *
     */
    private String code = SUCCESS;
    private String msg = "";
    private String exceptoin = "";
    private T data;
    private int row;
    private int size;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public JsonResult(){}

    public JsonResult(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public JsonResult(String code,String msg,String exceptoin){
        this.code = code;
        this.msg = msg;
        this.exceptoin = exceptoin;
    }
    public String getExceptoin() {
        return exceptoin;
    }

    public void setExceptoin(String exceptoin) {
        this.exceptoin = exceptoin;
    }
    public static JsonResult error(String code, String msg,String exceptoin) {
        return new JsonResult(code, msg,exceptoin);
    }
    public static JsonResult error(String code, String msg) {
        return new JsonResult(code, msg);
    }
    public static JsonResult error(ExceptionCode exceptionCode,String exceptoin) {
        return new JsonResult(exceptionCode.getCode(), exceptionCode.getMsg(),exceptoin);
    }
    public static JsonResult error(ExceptionCode exceptionCode) {
        return new JsonResult(exceptionCode.getCode(), exceptionCode.getMsg());
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (data instanceof List){
            this.size = ((List) data).size();
        }
        this.data = data;
    }
}
