package com.common.ip.free;

import com.alibaba.fastjson.JSONObject;
import com.common.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class FreeIPUtil {

    private static String URL = "http://ip.tianqiapi.com/";

    /**
     * ip地址识别
     * @param ip ip
     * @return
     */
    public static FreeAddress getAdress(String ip){
        Map params = new HashMap();
        params.put("ip",ip);
        String s = HttpUtil.doGet(URL, params);
        FreeAddress freeAddress = JSONObject.parseObject(s, FreeAddress.class);
        return freeAddress;
    }

    public static void main(String[] args) {
        getAdress("113.132.171.119");
    }
}
