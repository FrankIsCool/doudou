package com.common.ip.juhe;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;
import com.common.weather.juhe.JHResult;

import java.util.HashMap;
import java.util.Map;

public class JHIPUtil {

    private static String URL = "http://apis.juhe.cn/ip/ipNew";

    private static String KEY = "e73c70434584b2a5ee343f2cc6b49e99";
    /**
     * 获取地址
     * @param ip
     * @return
     */
    public static JHResult getAddress(String ip){
        Map params = new HashMap();
        params.put("ip",ip);
        params.put("key",KEY);
        String s = HttpUtil.doGet(URL, params);
        JHResult jhResult = JSONObject.parseObject(s, JHResult.class);
        JHIPData jhIPData = JSONObject.parseObject(jhResult.getResult().toString(), JHIPData.class);
        jhResult.setResult(jhIPData);
        return jhResult;
    }
    public static void main(String[] args) {
        JHIPUtil.getAddress("183.15.176.41");
    }

}
