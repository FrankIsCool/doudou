package com.common.weather.free;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class FreeWeatherUtil {

    private static String URL = "https://www.tianqiapi.com/api/";

    /**
     * 获取天气
     * @param city  城市名称,不要带市和区
     * @return
     */
    public static FreeResult getWeatherByCity(String city){
        Map params = new HashMap();
        params.put("version","v1");
        params.put("city",city);
        String s = HttpUtil.doGet(URL, params);
        FreeResult freeResult = JSONObject.parseObject(s, FreeResult.class);
        return freeResult;
    }
    /**
     * 获取天气
     * @param ip  ip
     * @return
     */
    public static FreeResult getWeatherByIP(String ip){
        Map params = new HashMap();
        params.put("version","v1");
        params.put("ip",ip);
        String s = HttpUtil.doGet(URL, params);
        FreeResult freeResult = JSONObject.parseObject(s, FreeResult.class);
        return freeResult;
    }
    public static void main(String[] args) {
        getWeatherByCity("上海");
    }
}
