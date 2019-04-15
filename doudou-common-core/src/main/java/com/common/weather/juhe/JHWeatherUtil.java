package com.common.weather.juhe;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询天气
 */
public class JHWeatherUtil {

    private static String URL = "http://apis.juhe.cn/";

    private static String KEY = "49f03a448a53530b298579f51f3d3023";

    /**
     * 获取天气
     * @param city  城市
     * @return
     */
    public static JHResult getWeather(String city){
        Map params = new HashMap();
        params.put("city",city);
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL+"simpleWeather/query", params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        JHWeatherData weatherJHData = JSONObject.parseObject(jhResult.getResult().toString(), JHWeatherData.class);
        jhResult.setResult(weatherJHData);
        return jhResult;
    }
    /**
     * 支持城市
     * @return
     */
    public static JHResult getCitys(){
        Map params = new HashMap();
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL+"/simpleWeather/cityList", params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        List<JHCity> jhCitys = JSONObject.parseArray(jhResult.getResult().toString(), JHCity.class);
        jhResult.setResult(jhCitys);
        return jhResult;
    }
    public static void main(String[] args) {
        JHWeatherUtil.getWeather("上海");
    }
}
